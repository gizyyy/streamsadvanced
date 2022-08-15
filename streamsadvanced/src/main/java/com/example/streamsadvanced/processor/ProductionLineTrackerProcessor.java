package com.example.streamsadvanced.processor;

import java.time.Duration;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.TimeWindows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonSerde;

import com.example.streamsadvanced.model.Incident;
import com.example.streamsadvanced.model.SummaryMetric;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ProductionLineTrackerProcessor {

	private final NewTopic incidentSummaryTopic;
	private final NewTopic incidentTopic;

	private final JsonSerde<Incident> incidentSerde;
	private final JsonSerde<SummaryMetric> incidentSummarySerde;

	@Bean
	public KStream<String, SummaryMetric> processSummary(StreamsBuilder kStreamBuilder) {

		KStream<String, SummaryMetric> stream = kStreamBuilder
				.stream(incidentTopic.name(), Consumed.with(Serdes.String(), incidentSerde)).groupByKey()
				.windowedBy(TimeWindows.of(Duration.ofMinutes(5))).count().filter((key, value) -> value > 50).toStream()
				.map((key, value) -> new KeyValue<>(key.key(), new SummaryMetric(value)))
				.peek((key, value) -> System.out.println(key + " " + value));

		stream.to(incidentSummaryTopic.name());
		return stream;
	}

}