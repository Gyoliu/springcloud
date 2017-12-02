package com.example.eureka_server02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 服务提供者
 */
@RestController
@EnableDiscoveryClient
@SpringBootApplication
@EnableHystrix
public class EurekaServer02Application {

	private Logger logger = LoggerFactory.getLogger(EurekaServer02Application.class);
	
	public static void main(String[] args) {
		SpringApplication.run(EurekaServer02Application.class, args);
	}

	@RequestMapping(value = "/hello2/1",method = RequestMethod.GET)
	public String hello(@RequestParam("a")int a,@RequestParam("b")int b){
		return "hello world !" + (a + b);
	}

	@RequestMapping(value = "/hello1/1",method = RequestMethod.GET)
	public String hello1(HttpServletRequest request, @RequestParam("a")int a, @RequestParam("b")int b){
		logger.info("=====<call server02 /hello/1, TraceId={}, SpanId={} > =====", request.getHeader("X-B3-TraceId"), request.getHeader("X-B3-SpanId"));
		return "hello world !" + (a + b);
	}
}
