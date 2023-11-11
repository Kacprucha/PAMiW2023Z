package com.example.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class BackenedApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackenedApplication.class, args);
	}

}
