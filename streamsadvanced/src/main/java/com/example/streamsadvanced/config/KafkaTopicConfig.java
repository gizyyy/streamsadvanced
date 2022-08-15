package com.example.streamsadvanced.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

	@Value("heat-metric-topic")
	private String heatTopic;

	@Value("sugar-metric-topic")
	private String sugarTopic;

	@Value("product-summary-topic")
	private String productTopic;

	@Value("incident-topic")
	private String incidentTopic;

	@Value("incident-summary-topic")
	private String incidentSummaryTopic;

	@Bean
	public NewTopic heatTopic() {
		return TopicBuilder.name(heatTopic).partitions(Integer.valueOf(1)).replicas(1).build();
	}

	@Bean
	public NewTopic sugarTopic() {
		return TopicBuilder.name(sugarTopic).partitions(Integer.valueOf(1)).replicas(1).build();
	}

	@Bean
	public NewTopic productTopic() {
		return TopicBuilder.name(productTopic).partitions(Integer.valueOf(1)).replicas(1).build();
	}

	@Bean
	public NewTopic incidentTopic() {
		return TopicBuilder.name(incidentTopic).partitions(Integer.valueOf(1)).replicas(1).build();
	}

	@Bean
	public NewTopic incidentSummaryTopic() {
		return TopicBuilder.name(incidentSummaryTopic).partitions(Integer.valueOf(1)).replicas(1).build();
	}

}