package com.gb.trip.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Detail {
	@JsonProperty
	private String addr1;
	@JsonProperty
	private String areacode;
	@JsonProperty
	private String contentid;
	@JsonProperty
	private String firstimage;
	@JsonProperty
	private String firstimage2;
	@JsonProperty
	private String homepage;
	@JsonProperty
	private String mapx;
	@JsonProperty
	private String mapy;
	@JsonProperty
	private String overview;
	@JsonProperty
	private String sigungucode;
	@JsonProperty
	private String title;
	@JsonProperty
	private String zipcode;
	@JsonProperty
	private String cat1;
	
	
}
