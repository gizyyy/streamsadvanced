package com.example.streamsadvanced.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
public class Incident implements KafkaMessage {
	private Integer productionLine;
	private Integer productSerialNumber;
	private Integer celcius;
	private Integer density;
}
