package com.example.eventmanagerbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class EventManagerBackEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventManagerBackEndApplication.class, args);
    }

}
