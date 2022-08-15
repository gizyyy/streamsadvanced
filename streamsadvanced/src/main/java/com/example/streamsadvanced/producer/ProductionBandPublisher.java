package com.example.streamsadvanced.producer;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.streamsadvanced.metricgenerator.ProductSummaryGenerator;
import com.example.streamsadvanced.metricgenerator.ProductionLineGenerator;
import com.example.streamsadvanced.model.ProductSummary;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ProductionBandPublisher {

	private final KafkaTemplate<String, ProductSummary> productKafkaTemplate;
	private final NewTopic productTopic;
	private final ProductSummaryGenerator productSummaryGenerator;
	private final ProductionLineGenerator productionLineGenerator;

	/**
	 * Generates a product each second in one production line.
	 */

	@Scheduled(fixedRate = 200)
	public void produce() {
		Integer line = productionLineGenerator.generate();
		productKafkaTemplate.send(productTopic.name(), line.toString(),
				new ProductSummary(line, productSummaryGenerator.generate()));
	}
}