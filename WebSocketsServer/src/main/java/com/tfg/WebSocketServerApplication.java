package com.tfg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class WebSocketServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebSocketServerApplication.class, args);
	}
}
