package com.gb.trip.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gb.trip.model.Item;
import com.gb.trip.model.RootInfo;


@Service
public class WeatherService {
   public List<Item> getitem(String base_date, String nx, String ny) {
      String json = "";
      RootInfo root = null;

      try {
         String serviceKey = "v8Rq%2FDVRJhUwjOVwm87fzxzOxJw7dponkZK3XSD0VKU%2FAhc2yNics%2BBD%2BlIkUea2KJYY2BZoMFO6L8CkID%2FZTw%3D%3D";
         String numOfRows = "10";
         String pageNo = "1";
         String base_time = "0500";

         URL url = new URL("http://apis.data.go.kr/1360000/VilageFcstInfoService/getVilageFcst?serviceKey="
               + serviceKey + "&numOfRows=" + numOfRows + "&pageNo=" + pageNo + "&base_date=" + base_date
               + "&base_time=" + base_time + "&nx=" + nx + "&ny=" + ny + "&dataType=JSON");

         BufferedReader bf;
         String r = "";
         // url 로 지정한 요청으로부터 받은 json 데이터를 1줄씩 읽어서 string으로 연결하기
         bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
         while ((r = bf.readLine()) != null) {

            json = json.concat(r);
         }

         //System.out.println(json); // 스트링 json

         ObjectMapper mapper = new ObjectMapper();
         mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); // java 오브젝트에 없는 프로퍼티로 생기는 오류 발생하지 않도록 설정
         root = mapper.readValue(json, RootInfo.class);
         
         //JsonNode node = mapper.readTree(json);
         //RootInfo root2 = mapper.readValue(node.findValue("items").toString(), RootInfo.class);                  
         //System.out.println("root"+root2); // 객체로 매핑된 결과 출력

      } catch (Exception e) {
         e.printStackTrace();
      }
      return root.getResponse().getBody().getItems().getItem();

   }

   public List<Item> getitemLong(String tmFc, String regId) {
      String json = "";
      RootInfo root = null;      
      try {
         String serviceKey = "v8Rq%2FDVRJhUwjOVwm87fzxzOxJw7dponkZK3XSD0VKU%2FAhc2yNics%2BBD%2BlIkUea2KJYY2BZoMFO6L8CkID%2FZTw%3D%3D";
         String numOfRows = "10";
         String pageNo = "1";
         String tmFctime = tmFc+"0600";
         System.out.println(regId);
         System.out.println(tmFctime);
         URL url = new URL("http://apis.data.go.kr/1360000/MidFcstInfoService/getMidLandFcst" + "?serviceKey="
               + serviceKey + "&numOfRows=" + numOfRows + "&pageNo=" + pageNo + "&regId=" + regId + "&tmFc=" + tmFctime
               + "&dataType=JSON");
         //System.out.println(url);
         BufferedReader bf;
         String r = "";
         // url 로 지정한 요청으로부터 받은 json 데이터를 1줄씩 읽어서 string으로 연결하기
         bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
         while ((r = bf.readLine()) != null) {

            json = json.concat(r);
         }
         //System.out.println(json);
         
         ObjectMapper mapper = new ObjectMapper();
         mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); // java 오브젝트에 없는 프로퍼티로 생기는 오류 발생하지 않도록 설정
         root = mapper.readValue(json, RootInfo.class);
         //System.out.println(root.getResponse().getBody().getItems().getItem());
      } catch (Exception e) {
         e.printStackTrace();
      }
      return root.getResponse().getBody().getItems().getItem();
   }
   public List<Item> getitemLongTemp( String tmFc, String regTemp) {
      String json = "";
      RootInfo root = null;      
      try {
         String serviceKey = "v8Rq%2FDVRJhUwjOVwm87fzxzOxJw7dponkZK3XSD0VKU%2FAhc2yNics%2BBD%2BlIkUea2KJYY2BZoMFO6L8CkID%2FZTw%3D%3D";
         String numOfRows = "10";
         String pageNo = "1";
         String tmFctime = tmFc+"0600";

         URL url = new URL("http://apis.data.go.kr/1360000/MidFcstInfoService/getMidTa" + "?serviceKey="
               + serviceKey + "&numOfRows=" + numOfRows + "&pageNo=" + pageNo + "&regId=" + regTemp + "&tmFc=" + tmFctime
               + "&dataType=JSON");

         BufferedReader bf;
         String r = "";
         // url 로 지정한 요청으로부터 받은 json 데이터를 1줄씩 읽어서 string으로 연결하기
         bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
         while ((r = bf.readLine()) != null) {
            json = json.concat(r);
         }
         ObjectMapper mapper = new ObjectMapper();
         mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); // java 오브젝트에 없는 프로퍼티로 생기는 오류 발생하지 않도록 설정
         root = mapper.readValue(json, RootInfo.class);
         
      } catch (Exception e) {
         e.printStackTrace();
      }
      return root.getResponse().getBody().getItems().getItem();
   }

}