package com.example.eureka_client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 服务提供者
 */
//@EnableConfigServer //配置服务
@EnableDiscoveryClient//注册服务和发现服务，开启 eureka 服务
@RestController
@SpringBootApplication
@EnableHystrix
public class EurekaServer01Application {

	private Logger logger = LoggerFactory.getLogger(EurekaServer01Application.class);

	public static void main(String[] args) {
		SpringApplication.run(EurekaServer01Application.class, args);
	}

	@RequestMapping(value = "/hello1/1", method = RequestMethod.GET)
	public String hello( @RequestParam("a")int a, @RequestParam("b")int b){
		return "hello world!" + (a + b);
	}
}
