package com.example.streamsadvanced.producer;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.streamsadvanced.metricgenerator.HeatMetricGenerator;
import com.example.streamsadvanced.model.HeatMetric;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class HeatMetricPublisher {

	private final KafkaTemplate<String, HeatMetric> heatKafkaTemplate;
	private final NewTopic heatTopic;
	private final HeatMetricGenerator heatMetricGenerator;
	private final AtomicInteger atomicInteger = new AtomicInteger();

	/**
	 * Measures the production lines heat each second.
	 */

	@Scheduled(fixedRate = 1000)
	public void produce() {
		Integer line = atomicInteger.incrementAndGet() % 5;
		heatKafkaTemplate.send(heatTopic.name(), line.toString(), new HeatMetric(line, heatMetricGenerator.generate()));
	}
}