package com.engineer.shifts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class ShiftsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShiftsApplication.class, args);
	}
}
