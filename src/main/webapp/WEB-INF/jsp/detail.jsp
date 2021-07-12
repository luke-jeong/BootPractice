<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>trip</title>
<link rel="stylesheet" href="resources/css/trip.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script type="text/javascript">
$(function() {
	setAtagHtml();
});

function setAtagHtml(){
	$('#aTag').find('a').text('홈페이지바로가기');
}


</script>

</head>
<body>
	<div class="cont_inner">
		<div class="infoBox"
			style="display: block; position: relative; width: 1200px; margin: 0 auto;">
			<article>
				<header class="camp_top_info">
					<h2 class="skip">관광지 정보</h2>
					<div class="camp_info_box">
						<div class="img_b">
							<img src="${detail.firstimage}" alt="${detail.title}">
						</div>
						<!--//S:table-->
						<div class="cont_tb">
							<table class="table">
								<caption></caption>
								<colgroup>
									<col style="width: 30%;">
									<col style="width: 70%;">
								</colgroup>
								<tbody>
									<tr>
										<th scope="col">관광지명</th>
										<td>${detail.title}</td>
									</tr>
									<tr>
										<th scope="col">관광지 주소</th>
										<td>${detail.addr1}</td>
									</tr>
									<tr>
										<th scope="col">관광 유형</th>
										<td>${detail.cat1}</td>
									</tr>
									<tr>
										<th scope="col">전화번호</th>
										<td><p>${intro.infocenter}</p></td>
									</tr>
									<tr>
										<th scope="col">운영시간</th>
										<td>${intro.usetime}${intro.restdate}</td>
									</tr>
									<tr>
										<th scope="col">홈페이지</th>
										<td id="aTag">${detail.homepage}</td>
									</tr>
									<tr>
										<th scope="col">주차장</th>
										<td>${intro.parking}</td>
									</tr>
									<tr>
										<th scope="col">체험 정보</th>
										<td>${intro.expguide}</td>
									</tr>
								</tbody>
							</table>
							<div class="btn_w">
								<button type="button" class="btn_bB1 best"
									onclick="fnCampLike(); return false">추천하기</button>
								<button type="button" class="btn_rB1 zzim"
									onclick="fnCampBkmk(); return false">즐겨찾기</button>
								<button type="button" class="btn_gB1 btn_modify"
									onclick="history.back();">목록으로</button>
							</div>
						</div>
						<!--//E:table-->

					</div>
				</header>
			</article>

		</div>
		<div class="intro"
			style="display: block; position: relative; width: 1200px; margin: 0 auto;">
				<hr>
				<div>
					<h3>상세 내용</h3>
				</div>
				<div>${detail.overview}</div>
				<hr>
			<div style="width: 100%; margin: 10px auto;display: flex;">
				<div style="flex:1; width:30%; box-sizing: border-box;">
				<img alt="img1" src=${img1} style="width: 358px;"> </div>
				<div style="flex:1;margin: 0px 5%; width:30%;box-sizing: border-box;">
				<img alt="img2" src=${img2} style="width: 358px;"></div>
				<div style="flex:1;width:30%;box-sizing: border-box;">
				<img alt="img3" src=${img3} style="width: 358px;"></div>
			</div>
				<hr>
				<div>
					<h3>위치</h3>
					<script type="text/javascript"
						src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=4tkk8m40ec"></script>
					<div id="map" style="width: 100%; height: 800px;"></div>
					<script>
					var map = new naver.maps.Map('map', {
					    center: new naver.maps.LatLng(${detail.mapy}, ${detail.mapx}),
					    zoom: 17,
					    zoomControl: true, //줌 컨트롤의 표시 여부
				        zoomControlOptions: { //줌 컨트롤의 옵션
				            position: naver.maps.Position.TOP_RIGHT
				        }
					});
					map.setOptions("mapTypeControl", true); //지도 유형 컨트롤의 표시 여부

					naver.maps.Event.addListener(map, 'zoom_changed', function (zoom) {
					    console.log('zoom:' + zoom);
					});

					var contentString = [

					        '<div class="iw_inner">',
					        ' <h3>${detail.title}</h3>',
					        ' <p>${detail.addr1}<br>',
					        ' <img src="${detail.firstimage}" width="55" height="55" alt="서울시청" class="thumb" /><br>',
					        ' ${intro.infocenter}<br>',
					        ' ${detail.homepage}',
					        ' </p>',
					        '</div>'
					].join('');

					var marker = new naver.maps.Marker({
					    position: new naver.maps.LatLng(${detail.mapy}, ${detail.mapx}),
					    map: map
					});
					
					var infowindow = new naver.maps.InfoWindow({
					    content: contentString
					});

					naver.maps.Event.addListener(marker, "click", function(e) {
					    if (infowindow.getMap()) {
					        infowindow.close();
					    } else {
					        infowindow.open(map, marker);
					    }
					});
					</script>
				</div>
		</div>
	</div>
</body>
</html>
