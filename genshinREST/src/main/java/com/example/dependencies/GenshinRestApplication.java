package com.example.dependencies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class GenshinRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(GenshinRestApplication.class, args);
	}

}
