package com.nails.nastya.nailsme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories(value = "com.nails.nastya.nailsme")
@EntityScan(value = "ru.sravni.mpl.request.sender")
public class NailsmeApplication {

    public static final String VERSION_URL = "/api/v1";

    public static void main(String[] args) {
        SpringApplication.run(NailsmeApplication.class, args);
    }

}
