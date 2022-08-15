package com.example.streamsadvanced.processor;

import java.time.Duration;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.JoinWindows;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.StreamJoined;
import org.apache.kafka.streams.kstream.ValueJoiner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonSerde;

import com.example.streamsadvanced.model.HeatMetric;
import com.example.streamsadvanced.model.Incident;
import com.example.streamsadvanced.model.ProductSummary;
import com.example.streamsadvanced.model.SugarMetric;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class IncidentTrackerProcessor {

	private final NewTopic sugarTopic;
	private final NewTopic heatTopic;
	private final NewTopic productTopic;
	private final NewTopic incidentTopic;
	private final JsonSerde<SugarMetric> sugarSerde;
	private final JsonSerde<HeatMetric> heatSerde;
	private final JsonSerde<ProductSummary> productSerde;
	private final JsonSerde<Incident> incidentSerde;

	@Bean
	public KStream<String, Incident> processIncidents(StreamsBuilder kStreamBuilder) {

		KStream<String, HeatMetric> heatStream = kStreamBuilder
				.stream(heatTopic.name(), Consumed.with(Serdes.String(), heatSerde))
				.filter((key, value) -> value.getCelcius() > 25);

		KStream<String, SugarMetric> sugarStream = kStreamBuilder
				.stream(sugarTopic.name(), Consumed.with(Serdes.String(), sugarSerde))
				.filter((key, value) -> value.getDensity() > 3);

		KStream<String, ProductSummary> productStream = kStreamBuilder.stream(productTopic.name(),
				Consumed.with(Serdes.String(), productSerde));

		ValueJoiner<HeatMetric, SugarMetric, Incident> incidentJoiner = (heatMetric, sugarMetric) -> Incident.builder()
				.celcius(heatMetric.getCelcius()).density(sugarMetric.getDensity())
				.productionLine(heatMetric.getProductionLine()).build();

		ValueJoiner<Incident, ProductSummary, Incident> incidentExtender = (incident, product) -> {
			incident.setProductSerialNumber(product.getProductSerialNumber());
			return incident;
		};

		KStream<String, Incident> incidentStream = heatStream.join(sugarStream, incidentJoiner,
				JoinWindows.of(Duration.ofSeconds(1L)), StreamJoined.with(Serdes.String(), heatSerde, sugarSerde));

		KStream<String, Incident> incidentStreamExtended = incidentStream
				.join(productStream, incidentExtender, JoinWindows.of(Duration.ofSeconds(1L)),
						StreamJoined.with(Serdes.String(), incidentSerde, productSerde))
				.peek((key, value) -> System.out.println(key + " " + value));

		incidentStreamExtended.to(incidentTopic.name());

		return incidentStream;

	}

}