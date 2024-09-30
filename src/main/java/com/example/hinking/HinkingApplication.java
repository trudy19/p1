package com.example.hinking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class HinkingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HinkingApplication.class, args);
	}

}
