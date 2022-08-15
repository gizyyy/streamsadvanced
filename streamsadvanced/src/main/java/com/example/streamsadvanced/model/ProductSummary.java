package com.example.streamsadvanced.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ProductSummary implements KafkaMessage {
	private Integer productionLine;
	private Integer productSerialNumber;
}
