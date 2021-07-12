package com.gb.trip.model;

import java.sql.Date;

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
public class Schedule {
	private int idx;
	private String addr;
	private Date date;
	private String contentid;
	private int userId;
}
