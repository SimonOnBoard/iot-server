package com.simononboard.iotserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableKafka
@EnableScheduling
public class IotServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(IotServerApplication.class, args);
    }

}
