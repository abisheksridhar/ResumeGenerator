package com.capgemini.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ResumeMakerEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResumeMakerEurekaServerApplication.class, args);
	}

}
