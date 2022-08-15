package com.example.streamsadvanced.metricgenerator;

import java.util.Random;
import java.util.function.Supplier;

import org.springframework.stereotype.Component;

@Component
public class HeatMetricGenerator implements MetricGenerator {

	public Integer generate() {
		Random random = new Random();
		Supplier<Integer> randomInt = () -> {
			return random.nextInt(20, 27);
		};
		return randomInt.get();
	}
}
