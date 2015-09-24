package com.sample.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableConfigServer
public class MainApp {
	public static void main(String[] args) {
		SpringApplication.run(MainApp.class, args);
	}
}
