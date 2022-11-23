package com.capgemini.userService;

import com.capgemini.userService.jwt.utility.JwtUtility;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import java.util.Properties;

@SpringBootApplication
@EnableEurekaClient
public class ResumeMakerUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResumeMakerUserServiceApplication.class, args);
	}

	@Bean
	PasswordEncoder encoder()
	{
		return new BCryptPasswordEncoder();
	}

	@Bean
	JavaMailSender mailSender()
	{
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.mailtrap.io");
		mailSender.setPort(2525);
		mailSender.setUsername("9a10ae59977dec");
		mailSender.setPassword("bae275a4ab3878");
		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");
		return mailSender;
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate(){
		RestTemplate rt = new RestTemplate();
		rt.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
		return rt;
	}

	@Bean
	JwtUtility utility(){
		return new JwtUtility();
	}
}
