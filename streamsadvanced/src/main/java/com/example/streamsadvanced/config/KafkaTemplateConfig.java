package com.example.streamsadvanced.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import com.example.streamsadvanced.model.HeatMetric;
import com.example.streamsadvanced.model.Incident;
import com.example.streamsadvanced.model.ProductSummary;
import com.example.streamsadvanced.model.SugarMetric;
import com.example.streamsadvanced.model.SummaryMetric;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class KafkaTemplateConfig {

	private final ProducerFactory<String, SugarMetric> sugarProducerFactory;
	private final ProducerFactory<String, HeatMetric> heatProducerFactory;
	private final ProducerFactory<String, ProductSummary> productProducerFactory;
	private final ProducerFactory<String, Incident> incidentProducerFactory;
	private final ProducerFactory<String, SummaryMetric> incidentSummaryProducerFactory;

	@Bean
	public KafkaTemplate<String, SugarMetric> sugarKafkaTemplate() {
		return new KafkaTemplate<String, SugarMetric>(sugarProducerFactory);
	}

	@Bean
	public KafkaTemplate<String, HeatMetric> heatKafkaTemplate() {
		return new KafkaTemplate<String, HeatMetric>(heatProducerFactory);
	}

	@Bean
	public KafkaTemplate<String, Incident> incidentKafkaTemplate() {
		return new KafkaTemplate<String, Incident>(incidentProducerFactory);
	}

	@Bean
	public KafkaTemplate<String, SummaryMetric> incidentSummaryKafkaTemplate() {
		return new KafkaTemplate<String, SummaryMetric>(incidentSummaryProducerFactory);
	}

	@Bean
	public KafkaTemplate<String, ProductSummary> productKafkaTemplate() {
		return new KafkaTemplate<String, ProductSummary>(productProducerFactory);
	}

}
