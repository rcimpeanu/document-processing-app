package com.example.document_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(scanBasePackages = "com.example")
@EnableAsync
@EnableRetry
public class DocumentServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(DocumentServiceApplication.class, args);
	}
}
