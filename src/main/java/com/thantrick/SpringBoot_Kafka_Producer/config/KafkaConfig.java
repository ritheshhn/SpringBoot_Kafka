package com.thantrick.SpringBoot_Kafka_Producer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic createNewTopic(){
        return new NewTopic("demoTopic3-from-Config", 5, (short) 1);
    }
}
