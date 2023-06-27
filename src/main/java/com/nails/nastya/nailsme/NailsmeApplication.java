package com.nails.nastya.nailsme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NailsmeApplication {

	public static final String VERSION_URL = "/api/v1";

	public static void main(String[] args) {
		SpringApplication.run(NailsmeApplication.class, args);
	}

}
