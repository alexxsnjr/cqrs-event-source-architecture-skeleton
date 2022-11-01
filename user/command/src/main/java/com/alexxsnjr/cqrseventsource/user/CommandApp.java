package com.alexxsnjr.cqrseventsource.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.alexxsnjr"})
public class CommandApp {

	public static void main(String[] args) {
		SpringApplication.run(CommandApp.class, args);
	}

}
