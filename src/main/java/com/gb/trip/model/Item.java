package com.gb.trip.model;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
	@JsonProperty
	private String category;
	@JsonProperty
	private String fcstValue;
	@JsonProperty
	private String nx;
	@JsonProperty
	private String ny;
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