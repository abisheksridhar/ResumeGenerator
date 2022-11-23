package com.capgemini.apiGateway;

import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.Base64Encoder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
public class ResumeMakerApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResumeMakerApiGatewayApplication.class, args);
	}
}
