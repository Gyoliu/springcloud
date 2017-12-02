package com.example.eureka_center;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 开启注册中心,@EnableEurekaServer这个
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaCenterApplication.class, args);
	}
}
