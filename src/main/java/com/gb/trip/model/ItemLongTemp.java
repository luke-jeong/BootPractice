package com.gb.trip.model;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemLongTemp {
	@JsonProperty
	private String taMax3;
	@JsonProperty
	private String taMax4;
	@JsonProperty
	private String taMax5;
	@JsonProperty
	private String taMax6;
	@JsonProperty
	private String taMax7;
	@JsonProperty
	private String taMax9;
	@JsonProperty
	private String taMax10;

}
