package com.example.kakaexample.controller;


import com.example.kakaexample.config.FileMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class SendMessage {

    private final KafkaTemplate<String,String> kafkaTemplate;


    public SendMessage(KafkaTemplate<String,String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    @PostMapping("/produce/file")
    public ResponseEntity<Map<String, String>> sendFileMessageKafka(@RequestPart("file") MultipartFile file,
                                                                    @RequestPart("type") String type,
                                                                    @RequestPart("raison_sociale") String raison_sociale) throws IOException {

        // Validate file
        if (file.isEmpty()) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Please select a file to upload");
            return ResponseEntity.badRequest().body(response);
        }

        // Convert multipart file to byte array
        byte[] fileBytes = file.getBytes();

        FileMessage message = new FileMessage();
        message.setFileBytes(fileBytes);
        message.setType(type);
        message.setRaisonSociale(raison_sociale);

        ObjectMapper objectMapper = new ObjectMapper(); // Jackson library
        String jsonMessage = objectMapper.writeValueAsString(message);

        // Send the file bytes to Kafka
        kafkaTemplate.send("company-" + raison_sociale, jsonMessage);

        Map<String, String> response = new HashMap<>();
        response.put("message", "File and type were sent to Kafka topic successfully: company-" + raison_sociale);
        return ResponseEntity.ok(response);
    }

}
