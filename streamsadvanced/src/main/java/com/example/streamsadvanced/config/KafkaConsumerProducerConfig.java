package com.example.streamsadvanced.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;

import com.example.streamsadvanced.model.HeatMetric;
import com.example.streamsadvanced.model.Incident;
import com.example.streamsadvanced.model.KafkaMessage;
import com.example.streamsadvanced.model.ProductSummary;
import com.example.streamsadvanced.model.SugarMetric;
import com.example.streamsadvanced.model.SummaryMetric;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class KafkaConsumerProducerConfig {

	private final ConsumerFactory<String, SugarMetric> sugarConsumerFactory;
	private final ConsumerFactory<String, HeatMetric> heatConsumerFactory;
	private final ConsumerFactory<String, ProductSummary> productConsumerFactory;
	private final ConsumerFactory<String, Incident> incidentConsumerFactory;
	private final ConsumerFactory<String, SummaryMetric> incidentSummaryConsumerFactory;

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, SugarMetric> sugarKafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, SugarMetric> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(sugarConsumerFactory);
		return factory;
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, HeatMetric> heatKafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, HeatMetric> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(heatConsumerFactory);
		return factory;
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, ProductSummary> productkafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, ProductSummary> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(productConsumerFactory);
		return factory;
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Incident> incidentkafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, Incident> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(incidentConsumerFactory);
		return factory;
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, SummaryMetric> incidentSummaryKafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, SummaryMetric> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(incidentSummaryConsumerFactory);
		return factory;
	}
}
