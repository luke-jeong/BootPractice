package com.gb.trip.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import com.gb.trip.model.Schedule;

@Mapper
public interface ScheduleMapper {
	@Insert("INSERT INTO schedule(title,addr,date,contentid,userId) VALUES(#{title},#{addr},#{date},#{contentid},#{userId})")
	@Options(useGeneratedKeys = true, keyProperty = "idx")
	int insertSechdule(@Param("schedule") Schedule schedule);
}
