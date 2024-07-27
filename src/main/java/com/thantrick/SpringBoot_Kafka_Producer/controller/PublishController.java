package com.thantrick.SpringBoot_Kafka_Producer.controller;

import com.thantrick.SpringBoot_Kafka_Producer.service.KafkaPublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/publish")
public class PublishController {

    @Autowired
    private KafkaPublisherService kafkaPublisherService;

    @GetMapping("/{msg}")
    public ResponseEntity<?> publishMessage(@PathVariable String msg){
        try{

            kafkaPublisherService.publishMessageToTopic(msg);
            return ResponseEntity.ok("Message published Successfully....!");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
}
