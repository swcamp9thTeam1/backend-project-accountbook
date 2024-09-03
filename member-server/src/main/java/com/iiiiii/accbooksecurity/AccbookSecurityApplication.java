package com.iiiiii.accbooksecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AccbookSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccbookSecurityApplication.class, args);
    }

}
