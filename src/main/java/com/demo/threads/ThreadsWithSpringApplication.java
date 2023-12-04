package com.demo.threads;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ThreadsWithSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThreadsWithSpringApplication.class, args);
	}

}
