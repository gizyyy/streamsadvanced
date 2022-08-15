package com.example.streamsadvanced.metricgenerator;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Component;

@Component
public class ProductSummaryGenerator implements MetricGenerator {

	private final AtomicInteger atomicInteger = new AtomicInteger();

	public Integer generate() {
		return atomicInteger.incrementAndGet();
	}
}
