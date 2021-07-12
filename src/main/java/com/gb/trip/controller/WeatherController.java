package com.gb.trip.controller;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gb.trip.model.Area;
import com.gb.trip.model.Item;
import com.gb.trip.service.WeatherService;

/*
    @RestController : 기본으로 하위에 있는 메소드들은 모두 @ResponseBody를 가지게 된다.
    @RequestBody : 클라이언트가 요청한 XML/JSON을 자바 객체로 변환해서 전달 받을 수 있다.
    @ResponseBody : 자바 객체를 XML/JSON으로 변환해서 응답 객체의 Body에 실어 전송할 수 있다.
            클라이언트에게 JSON 객체를 받아야 할 경우는 @RequestBody, 자바 객체를 클라이언트에게 JSON으로 전달해야할 경우에는 @ResponseBody 어노테이션을 붙여주면 된다. 
    @ResponseBody를 사용한 경우 View가 아닌 자바 객체를 리턴해주면 된다.
*/
@RestController
public class WeatherController {

	@Autowired
	WeatherService service;
	List<Area> area;

	@RequestMapping(value = "/search_date",headers = {"Content-type=application/json"}, method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> search_date(@RequestBody Map<String,String> map) throws ParseException {
		if (area == null)
			setData();
		
		if(map.get("areacode") == null || map.get("areacode")=="") {
			String img = "resources/img/unnamed.jpg";
			String temp = "지역정보 없음";
			map.put("img", img);
			map.put("temp", temp);
			return map;
		}
			
		
		
		int j = 0;
		for (j = 0; j < area.size(); j++) {
			if (area.get(j).getAreacode().equals(map.get("areacode"))) {
				break;
			}
		}
		Area result = area.get(j);
		
		
		/*------------------날짜 변환 ----------------------*/
		
		LocalDate s_date_local = LocalDate.parse(map.get("s_date"), DateTimeFormatter.ISO_DATE);
		LocalDate today = LocalDate.now();
		
		Period period = Period.between(today, s_date_local);	//사용자가 선택한 날짜와 기준날짜 간격 계산.
				
		String str_date = today.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		
		
		
		/*---------------------날짜 차이 별 실행 api 달라짐--------------------------------*/
		
		if(period.getDays()<0) {
//			modelMap.addAttribute("alert","이전 날짜의 날씨 조회는 불가능합니다.");
//			mv = new ModelAndView("search_date", modelMap);
//			
		}else if(0<= period.getDays()&&period.getDays()<=2) {
			List<Item> item = service.getitem(str_date, result.getNx(), result.getNy());
			
			String img = "";
			int mode = 0;
			for(Item i : item) {
				if(i.getCategory().equals("PTY") && i.getFcstValue().equals("1")||i.getCategory().equals("PTY") && i.getFcstValue().equals("4")
						||i.getCategory().equals("PTY") && i.getFcstValue().equals("5")) {					
					img = "resources/img/cloud/1비.png";	
					mode = 1;
				}else if(i.getCategory().equals("PTY") && i.getFcstValue().equals("2") || i.getCategory().equals("PTY") && i.getFcstValue().equals("6")) {
					img = "resources/img/cloud/2비눈.png";
					mode = 1;
				}else if(i.getCategory().equals("PTY") && i.getFcstValue().equals("3") || i.getCategory().equals("PTY") && i.getFcstValue().equals("7")) {
					img = "resources/img/cloud/3눈.png";	
					mode = 1;
				}else if( i.getCategory().equals("SKY") && i.getFcstValue().equals("1")) {
					img = "resources/img/cloud/1맑음.png";
				}else if( i.getCategory().equals("SKY") && i.getFcstValue().equals("3")) {
					img = "resources/img/cloud/3구름많음.png";
				}else if( i.getCategory().equals("SKY") && i.getFcstValue().equals("4")) {
					img = "resources/img/cloud/3구름많음.png";
				}
			}
					String temp = "";
				for(Item t: item) {
					if(t.getCategory().equals("T3H")) {
						temp = t.getFcstValue();
					}
				}
				
				map.put("img", img);
	     		map.put("temp", temp);
		
		}else if(3<=period.getDays()&&period.getDays()<=10){	//과거 날짜를 선택한 경우 예보 출력 불가.
			List<Item> itemLongSky = service.getitemLong(str_date, result.getRegId());
     		List<Item> itemLongTemp	= service.getitemLongTemp(str_date, result.getRegTemp());
     		
			String img = "";
				for(Item i : itemLongSky ) {
					if(i.getWf3Am().equals("구름많고 비") ||i.getWf3Am().equals("흐리고 비")) {					
						img = "resources/img/cloud/1비.png";	
					}else if(i.getWf3Am().equals("구름많고 비/눈") ||i.getWf3Am().equals("흐리고 비/눈")) {
						img = "resources/img/cloud/2비눈.png";
					}else if(i.getWf3Am().equals("흐리고눈") ||i.getWf3Am().equals("구름많고 눈")) {
						img = "resources/img/cloud/3눈.png";	
					}else if(i.getWf3Am().equals("구름많고 소나기") ||i.getWf3Am().equals("흐리고 소나기")) {
						img = "resources/img/cloud/4소나기.png";
					}else if(i.getWf3Am().equals("맑음") ) {
						img = "resources/img/cloud/1맑음.png";
					}else if(i.getWf3Am().equals("구름많음") ||i.getWf3Am().equals("흐림")) {
						img = "resources/img/cloud/3구름많음.png";
					}
				}
			String temp = "";
				for(Item t: itemLongTemp) {					
					temp = t.getTaMax3();
				}
     		map.put("img", img);
     		map.put("temp", temp);
     				
     	}else if(11<=period.getDays()) {
//     		modelMap.addAttribute("alert","11일 이후 날씨 조회는 불가능합니다.");
//			mv = new ModelAndView("search_date", modelMap);
     	}		
		return map;		
	}

/*-------------------------------사용자가 선택한 도시별 코드 부여-------------------------------------*/
	private void setData() {
		area = new ArrayList<Area>();
		area.add(new Area("32", "강원도", "73", "135","11D20000","11D20501"));
		area.add(new Area("31", "경기도", "60", "121","11B00000","11B10101"));
		area.add(new Area("36", "경상남도", "90", "77","11H20000","11H10201"));
		area.add(new Area("35", "경상북도", "102", "94","11H10000","11H10701"));
		area.add(new Area("5", "광주광역시", "60", "74","11F20000","11F20501"));
		area.add(new Area("4", "대구광역시", "89", "90","11H10000","11H10701"));
		area.add(new Area("3", "대전광역시", "68", "100","11C20000","11C20404"));
		area.add(new Area("6", "부산광역시", "98", "75","11H20000","11H10201"));
		area.add(new Area("1", "서울특별시", "60", "127","11B00000","11B10101"));
		area.add(new Area("8", "세종시", "66", "105","11C20000","11C20404"));
		area.add(new Area("7", "울산광역시", "102", "84","11H20000","11H10201"));
		area.add(new Area("2", "인천광역시", "56", "124","11B00000","11B10101"));
		area.add(new Area("38", "전라남도", "50", "67","11F20000","11F20501"));
		area.add(new Area("37", "전라북도", "63", "89","11F10000","11F10201"));
		area.add(new Area("39", "제주특별자치도", "60", "38","11G00000","11G00401"));
		area.add(new Area("34", "충청남도", "63", "110","11C20000","11C20404"));
		area.add(new Area("33", "충청북도", "69", "106","11C10000","11C20401"));

	}

}