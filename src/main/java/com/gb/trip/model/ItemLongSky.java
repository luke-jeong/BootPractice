package com.gb.trip.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemLongSky {
	@JsonProperty
	private String wf3Am;
	@JsonProperty
	private String wf4Am;
	@JsonProperty
	private String wf5Am;
	@JsonProperty
	private String wf6Am;
	@JsonProperty
	private String wf7Am;
	@JsonProperty
	private String wf8;
	@JsonProperty
	private String wf9;
	@JsonProperty
	private String wf10;

}