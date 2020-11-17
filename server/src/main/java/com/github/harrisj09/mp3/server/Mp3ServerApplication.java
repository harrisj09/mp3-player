package com.github.harrisj09.mp3.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;


@Service
@SpringBootApplication
public class Mp3ServerApplication {

	/*
	TODO
		- return file using spring
		- handling requests (of ids)
		- On startup index folder as hashmap
	 */
	public static void main(String[] args) {
		SpringApplication.run(Mp3ServerApplication.class, args);
	}
}
