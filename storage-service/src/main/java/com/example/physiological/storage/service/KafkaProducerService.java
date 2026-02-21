package com.example.physiological.storage.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${storage.kafka.topic}")
    private String topic;

    public void sendMessage(String key, String value) {
        kafkaTemplate.send(topic, key, value);
    }
}
