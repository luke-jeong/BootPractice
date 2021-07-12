package com.gb.trip.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Body {
	
	@JsonProperty
	private Items items;
	@JsonProperty
	private int nomOfRows;
	@JsonProperty
	private int pageNo;
	@JsonProperty
	private int totalCount;

}
