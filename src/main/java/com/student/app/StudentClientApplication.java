package com.student.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
public class StudentClientApplication {
	private static final Logger logger = LoggerFactory.getLogger(StudentClientApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(StudentClientApplication.class, args);
		logger.info("...........in StudentDetails1Application");
	}

}
