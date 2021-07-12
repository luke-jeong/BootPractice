package com.gb.trip.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Intro {
	@JsonProperty
	private String infocenter; //전화번호
	@JsonProperty
	private String parking;	//주차장
	@JsonProperty
	private String usetime;	//이용시간
	@JsonProperty
	private String restdate; //휴일
	@JsonProperty
	private String expguide; //체험가이드
	
}
