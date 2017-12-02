package com.example.feignwithhystrix;

import com.example.feignwithhystrix.web.ComputeClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@EnableHystrixDashboard
@EnableCircuitBreaker
@RestController
public class FeignWithHystrixApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeignWithHystrixApplication.class, args);
	}

	@Autowired
	ComputeClient computeClient;

	@GetMapping("/hello")
	@HystrixCommand(fallbackMethod = "helloServiceFallback")
	public String findByIdFeign(@RequestParam("a")int a, @RequestParam("b")int b) {
		return computeClient.hello(a, b);
	}

	public String helloServiceFallback(int a,int b){
		return "error" + (a+b);
	}

}
