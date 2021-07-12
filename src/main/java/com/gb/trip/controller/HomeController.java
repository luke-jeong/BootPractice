package com.gb.trip.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gb.trip.model.Detail;
import com.gb.trip.model.Intro;
import com.gb.trip.model.Page;
import com.gb.trip.service.ApiService;

@Controller
public class HomeController {
	
	@Autowired
	private ApiService apiService; 
	
	@GetMapping("/list")
	public void list(Model model, @RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage,
			@RequestParam(value="areacode", required = false, defaultValue = "")String areacode, 
			@RequestParam(value = "sigungucode", required = false, defaultValue = "")String sigungucode,
			@RequestParam(value = "arrange", required = false, defaultValue = "R")String arrange,
			@RequestParam(value = "numOfPage", required = false, defaultValue = "20")String numOfPage,			
			@RequestParam(value = "s_date", required = false, defaultValue = "20")String s_date			
	) throws IOException {
		int totalCnt = apiService.getCount(areacode, sigungucode);
		Page page = new Page(currentPage,Integer.parseInt(numOfPage), totalCnt);
		model.addAttribute("page", page);
		model.addAttribute("areacode",areacode);
		model.addAttribute("sigungucode",sigungucode);
		model.addAttribute("arrange",arrange);
		model.addAttribute("numOfPage",numOfPage);
		model.addAttribute("s_date",s_date);
		
	}
	
	@GetMapping("/detail")
	public void detail(Model model, @RequestParam Map<String,String> map) throws IOException {
		Detail detail = apiService.getDetail(map.get("contentid"));
		Intro intro = apiService.getIntro(map.get("contentid"));
		if(detail.getCat1().equals("A01")||detail.getCat1()!=null) 
			detail.setCat1("자연");
		else if(detail.getCat1().equals("A02")||detail.getCat1()!=null){
			detail.setCat1("인문");
		}else if(detail.getCat1().equals("A03")||detail.getCat1()!=null){
			detail.setCat1("레포츠");
		}else if(detail.getCat1().equals("A04")||detail.getCat1()!=null){
			detail.setCat1("쇼핑");
		}
	
		model.addAttribute("state", map);
		model.addAttribute("detail", detail);
		model.addAttribute("intro",intro);
		model.addAttribute("img1",apiService.getImg(map.get("contentid")).size()<1||map.get("contentid")==null?"resources/img/unnamed.jpg":apiService.getImg(map.get("contentid")).get(0));
		model.addAttribute("img2",apiService.getImg(map.get("contentid")).size()<2||map.get("contentid")==null?"resources/img/unnamed.jpg":apiService.getImg(map.get("contentid")).get(1));
		model.addAttribute("img3",apiService.getImg(map.get("contentid")).size()<3||map.get("contentid")==null?"resources/img/unnamed.jpg":apiService.getImg(map.get("contentid")).get(2));
	}
	
}
