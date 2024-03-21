package com.example.kakaexample;

import org.apache.kafka.common.protocol.types.Field;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class KakaExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(KakaExampleApplication.class, args);
    }


}
