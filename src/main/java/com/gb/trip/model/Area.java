package com.gb.trip.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Area {
	private String areacode;
	
	private String areaname;
	@JsonProperty
	private String nx;
	@JsonProperty
	private String ny;
	@JsonProperty
	private String regId;
	@JsonProperty
	private String regTemp;
}
