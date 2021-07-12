package com.gb.trip.service;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.gb.trip.model.Detail;
import com.gb.trip.model.Intro;

public interface ApiService {
	public int getCount(String area, String area2)throws  IOException;
	public Detail getDetail(String contentid) throws IOException;
	public Intro getIntro(String contentid) throws IOException;
	public List<JsonNode> getImg(String contentid)throws IOException;
}
