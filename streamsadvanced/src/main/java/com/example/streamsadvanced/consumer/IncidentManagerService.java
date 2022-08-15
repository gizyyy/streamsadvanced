package com.example.streamsadvanced.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.example.streamsadvanced.model.Incident;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class IncidentManagerService {

	@KafkaListener(topics = "incident-topic", groupId = "group-high", concurrency = "2")
	public void listen(@Payload Incident kafkaMessage, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {

		System.out.println(String.format("Incident recieved serial number: %s -  production line :%s",
				kafkaMessage.getProductSerialNumber(), kafkaMessage.getProductionLine()));
	}

}