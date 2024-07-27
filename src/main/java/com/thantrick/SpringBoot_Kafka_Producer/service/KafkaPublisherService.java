package com.thantrick.SpringBoot_Kafka_Producer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaPublisherService {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void publishMessageToTopic(String msg){
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send("demoTopic1", msg);
        future.whenComplete((result, e) -> {
           if(e == null){
               System.out.println("Sent message ["+ msg +"] and offset ["+result.getRecordMetadata().offset()+"]");
           }
           else{
               System.out.println("Unable to publish message ["+msg+"] due to "+e.getMessage());
           }
        });
    }
}
