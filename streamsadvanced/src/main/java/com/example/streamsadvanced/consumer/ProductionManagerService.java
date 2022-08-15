package com.example.streamsadvanced.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductionManagerService {

	@KafkaListener(topics = "incident-summary-topic", groupId = "group-summary", concurrency = "1")
	public void listen(@Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
			@Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
		System.out.println(String.format("Production line: %s alerting!", key));
	}

}