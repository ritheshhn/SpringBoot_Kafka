package com.thantrick.SpringBoot_Kafka_Producer.controller;

import com.thantrick.SpringBoot_Kafka_Producer.dto.Employee;
import com.thantrick.SpringBoot_Kafka_Producer.service.KafkaPublisherService;
import com.thantrick.SpringBoot_Kafka_Producer.util.CsvFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publish")
public class PublishController {

    @Autowired
    private KafkaPublisherService kafkaPublisherService;

    @GetMapping("/test")
    public String test(){
        return "Testing OK....!";
    }

    @GetMapping("/single-msg/{msg}")
    public ResponseEntity<?> publishMessage(@PathVariable String msg){
        try{
            kafkaPublisherService.publishMessageToTopic(msg);
            return ResponseEntity.ok("Message published Successfully....!");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @GetMapping("/bulk-msg")
    public ResponseEntity<?> publishBulkMessage(){
        try{
            for (int i = 0; i < 10000; i++) {
                kafkaPublisherService.publishMessageToTopic("msg" + i);
            }

            return ResponseEntity.ok("Bulk Message published Successfully....!");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @PostMapping("/single/employee")
    public ResponseEntity<?> publishEmployee(@RequestBody Employee employee){
        try{
            kafkaPublisherService.publishEmployeeToTopic(employee);

            return ResponseEntity.ok("Employee published Successfully....!");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @GetMapping("/bulk-emp")
    public ResponseEntity<?> publishBulkEmployees(){
        try {
            List<Employee> users = CsvFileReader.readDataFromCsv();
            users.forEach(emp -> kafkaPublisherService.publishEmployeeToTopic(emp));
            return ResponseEntity.ok("Message published successfully");
        } catch (Exception exception) {
            return ResponseEntity.
                    status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }

    }
}
