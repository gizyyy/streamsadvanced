package com.example.streamsadvanced.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serdes.LongSerde;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.streams.StreamsConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerde;

import com.example.streamsadvanced.model.HeatMetric;
import com.example.streamsadvanced.model.Incident;
import com.example.streamsadvanced.model.ProductSummary;
import com.example.streamsadvanced.model.SugarMetric;
import com.example.streamsadvanced.model.SummaryMetric;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers.LongDeserializer;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class KafkaBaseConfig {

	private final KafkaAdmin admin;
	private final JsonSerde<SugarMetric> sugarMetricSerde;
	private final JsonSerde<HeatMetric> heatMetricSerde;
	private final JsonSerde<ProductSummary> productSummarySerde;
	private final JsonSerde<Incident> incidentMetricSerde;
	private final JsonSerde<SummaryMetric> incidentSummarySerde;

	@Bean
	public ProducerFactory<String, SugarMetric> sugarMetricFactory() {
		HashMap<String, Object> configMap = new HashMap<>();
		configMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
				admin.getConfigurationProperties().get(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG));
		configMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, sugarMetricSerde.serializer().getClass());
		return new DefaultKafkaProducerFactory<String, SugarMetric>(configMap);
	}

	@Bean
	public ConsumerFactory<String, SugarMetric> sugarMetricConsumerFactory() {
		Map<String, Object> configMap = new HashMap<String, Object>();
		configMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
				admin.getConfigurationProperties().get(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG));
		configMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
		configMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
		configMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, sugarMetricSerde.deserializer().getClass());
		configMap.put(JsonDeserializer.TRUSTED_PACKAGES, "com.example.streamsadvanced.model");
		return new DefaultKafkaConsumerFactory<String, SugarMetric>(configMap);
	}

	@Bean
	public ProducerFactory<String, HeatMetric> heatMetricProducerFactory() {
		HashMap<String, Object> configMap = new HashMap<>();
		configMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
				admin.getConfigurationProperties().get(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG));
		configMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, heatMetricSerde.serializer().getClass());
		return new DefaultKafkaProducerFactory<String, HeatMetric>(configMap);
	}

	@Bean
	public ConsumerFactory<String, HeatMetric> heatMetricConsumerFactory() {
		Map<String, Object> configMap = new HashMap<String, Object>();
		configMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
				admin.getConfigurationProperties().get(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG));
		configMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
		configMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
		configMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, heatMetricSerde.deserializer().getClass());
		configMap.put(JsonDeserializer.TRUSTED_PACKAGES, "com.example.streamsadvanced.model");
		return new DefaultKafkaConsumerFactory<String, HeatMetric>(configMap);
	}

	@Bean
	public ProducerFactory<String, ProductSummary> productProducerFactory() {
		HashMap<String, Object> configMap = new HashMap<>();
		configMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
				admin.getConfigurationProperties().get(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG));
		configMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, productSummarySerde.serializer().getClass());
		return new DefaultKafkaProducerFactory<String, ProductSummary>(configMap);
	}

	@Bean
	public ConsumerFactory<String, ProductSummary> productConsumerFactory() {
		Map<String, Object> configMap = new HashMap<String, Object>();
		configMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
				admin.getConfigurationProperties().get(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG));
		configMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
		configMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
		configMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, productSummarySerde.deserializer().getClass());
		configMap.put(JsonDeserializer.TRUSTED_PACKAGES, "com.example.streamsadvanced.model");
		return new DefaultKafkaConsumerFactory<String, ProductSummary>(configMap);
	}

	@Bean
	public ProducerFactory<String, Incident> incidentProducerFactory() {
		HashMap<String, Object> configMap = new HashMap<>();
		configMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
				admin.getConfigurationProperties().get(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG));
		configMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, incidentMetricSerde.serializer().getClass());
		return new DefaultKafkaProducerFactory<String, Incident>(configMap);
	}

	@Bean
	public ConsumerFactory<String, Incident> incidentConsumerFactory() {
		Map<String, Object> configMap = new HashMap<String, Object>();
		configMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
				admin.getConfigurationProperties().get(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG));
		configMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
		configMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
		configMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, incidentMetricSerde.deserializer().getClass());
		configMap.put(JsonDeserializer.TRUSTED_PACKAGES, "com.example.streamsadvanced.model");
		return new DefaultKafkaConsumerFactory<String, Incident>(configMap);
	}

	@Bean
	public ProducerFactory<String, SummaryMetric> incidentSummaryProducerFactory() {
		HashMap<String, Object> configMap = new HashMap<>();
		configMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
				admin.getConfigurationProperties().get(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG));
		configMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, incidentSummarySerde.serializer().getClass());
		return new DefaultKafkaProducerFactory<String, SummaryMetric>(configMap);
	}

	@Bean
	public ConsumerFactory<String, SummaryMetric> incidentSummaryConsumerFactory() {
		Map<String, Object> configMap = new HashMap<String, Object>();
		configMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
				admin.getConfigurationProperties().get(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG));
		configMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
		configMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
		configMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, incidentSummarySerde.deserializer().getClass());
		configMap.put(JsonDeserializer.TRUSTED_PACKAGES, "com.example.streamsadvanced.model");
		return new DefaultKafkaConsumerFactory<String, SummaryMetric>(configMap);
	}

	@Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
	public KafkaStreamsConfiguration kStreamsConfigs() {
		Map<String, Object> configMap = new HashMap<String, Object>();
		configMap.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,
				admin.getConfigurationProperties().get(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG));
		configMap.put(StreamsConfig.APPLICATION_ID_CONFIG, "app");
		configMap.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		configMap.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, JsonSerde.class);
		return new KafkaStreamsConfiguration(configMap);
	}

}
