package com.example.ribbon_consumer;

import com.example.ribbon_consumer.web.ComputeClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients//注解开启Feign功能
/**
 * 当有三个服务ａ依赖于ｂ，ｂ依赖于ｃ
 * 当ｃ服务不能访问时，会导致ｂ一直请求不到如何东西，而这时候ａ访问ｂ，结果发现ｂ一直在调用ｃ，并且不能正确访问，
 * ｃ服务死了，这种现象被称为服务雪崩效应导致ａｂ服务的连锁反应，
 * 服务雪崩效应描述的是一种因服务提供者的不可用导致服务消费者的不可用，并将不可用逐渐放大的过程。
 */
@EnableCircuitBreaker//开启断路器
@EnableHystrixDashboard
@RestController
//@SpringCloudApplication == @SpringBootApplication @EnableCircuitBreaker断路器 @EnableDiscoveryClient 服务注册
public class RibbonConsumerApplication {

	private Logger logger = LoggerFactory.getLogger(RibbonConsumerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(RibbonConsumerApplication.class, args);
	}

	/**
	 *  提供负载均衡@LoadBalanced
	 * @return
	 */
	@Bean
	@LoadBalanced
    RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@RequestMapping("/hello")
	@HystrixCommand(fallbackMethod = "helloServiceFallback")
	public String add(){
		return restTemplate().getForObject("http://COMPUTE-SERVICE/hello", String.class);
	}

	@RequestMapping("/hello/a")
	@HystrixCommand(fallbackMethod = "helloServiceFallback")
	public String a(){
		return restTemplate().getForObject("http://COMPUTE-SERVICEA/hello1/1?a=1&b=1", String.class);
	}

	@RequestMapping("/hello/b")
	@HystrixCommand(fallbackMethod = "helloServiceFallback")
	public String b(){
		return restTemplate().getForObject("http://COMPUTE-SERVICEB/hello2/1?a=1&b=1", String.class);
	}

	public String helloServiceFallback(){
		return "error";
	}

	@Autowired
    ComputeClient computeClient;

	@RequestMapping(value = "/hello1", method = RequestMethod.GET)
	public String add1() {
		return computeClient.add();
	}

	/*@Autowired
	private DiscoveryClient client;

	@RequestMapping(value = "/hello1", method = RequestMethod.GET)
	public String hello2() {
		return client.getLocalServiceInstance();
	}*/

}

