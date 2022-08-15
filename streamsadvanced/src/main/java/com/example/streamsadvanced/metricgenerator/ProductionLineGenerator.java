package com.example.streamsadvanced.metricgenerator;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Component;

@Component
public class ProductionLineGenerator implements MetricGenerator {

	private final AtomicInteger atomicIntegerProductLine = new AtomicInteger();

	public Integer generate() {
		return atomicIntegerProductLine.incrementAndGet() % 5;
	}
}
