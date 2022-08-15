package com.example.streamsadvanced.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.kafka.support.serializer.JsonSerde;

import com.example.streamsadvanced.model.HeatMetric;
import com.example.streamsadvanced.model.Incident;
import com.example.streamsadvanced.model.ProductSummary;
import com.example.streamsadvanced.model.SugarMetric;
import com.example.streamsadvanced.model.SummaryMetric;

@Configuration
public class SerializationConfig {

	@Bean
	public JsonSerde<SugarMetric> sugarMetricSerde(com.fasterxml.jackson.databind.ObjectMapper objectMapper) {
		JsonSerde<SugarMetric> serde = new JsonSerde<>(SugarMetric.class, objectMapper);
		serde.deserializer().addTrustedPackages("*");
		return serde;
	}

	@Bean
	public JsonSerde<HeatMetric> heatMetricSerde(com.fasterxml.jackson.databind.ObjectMapper objectMapper) {
		JsonSerde<HeatMetric> serde = new JsonSerde<>(HeatMetric.class, objectMapper);
		serde.deserializer().addTrustedPackages("*");
		return serde;
	}

	@Bean
	public JsonSerde<ProductSummary> productSummarySerde(com.fasterxml.jackson.databind.ObjectMapper objectMapper) {
		JsonSerde<ProductSummary> serde = new JsonSerde<>(ProductSummary.class, objectMapper);
		serde.deserializer().addTrustedPackages("*");
		return serde;
	}

	@Bean
	public JsonSerde<Incident> incidentSerde(com.fasterxml.jackson.databind.ObjectMapper objectMapper) {
		JsonSerde<Incident> serde = new JsonSerde<>(Incident.class, objectMapper);
		serde.deserializer().addTrustedPackages("*");
		return serde;
	}

	@Bean
	public JsonSerde<SummaryMetric> incidentSummarySerde(com.fasterxml.jackson.databind.ObjectMapper objectMapper) {
		JsonSerde<SummaryMetric> serde = new JsonSerde<>(SummaryMetric.class, objectMapper);
		serde.deserializer().addTrustedPackages("*");
		return serde;
	}

	@Bean
	public StringJsonMessageConverter jsonConverter() {
		return new StringJsonMessageConverter();
	}
}
