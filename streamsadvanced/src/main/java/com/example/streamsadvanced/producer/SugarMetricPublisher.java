package com.example.streamsadvanced.producer;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.streamsadvanced.metricgenerator.SugarMetricGenerator;
import com.example.streamsadvanced.model.SugarMetric;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class SugarMetricPublisher {

	private final KafkaTemplate<String, SugarMetric> sugarKafkaTemplate;
	private final NewTopic sugarTopic;
	private final SugarMetricGenerator sugarMetricGenerator;
	private final AtomicInteger atomicInteger = new AtomicInteger();

	/**
	 * Measures the production lines sugar density each 5 second.
	 */
	
	@Scheduled(fixedRate = 1000)
	public void produce() {
		Integer line = atomicInteger.incrementAndGet() % 5;
		sugarKafkaTemplate.send(sugarTopic.name(), line.toString(),
				new SugarMetric(line, sugarMetricGenerator.generate()));
	}
}