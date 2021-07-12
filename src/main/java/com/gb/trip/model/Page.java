
package com.gb.trip.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Page {
	//생성자로 전달받을값
		private int currentPage; 	//현재페이지
		private int pageSize;		//한 화면에 띄울 게시글 limit 범위
		private int totalCount;		//전체 게시글
		
		//계산에 필요한 값
		private int startNo;		//limit 시작 인덱스
		private int totalPage;		//전체 페이지 개수       처음 <1 2 3> 끝
		private int startPage;		//페이지 안에 띄울 게시글 시작 idx
		private int endPage;		//페이지 안에 띄울 게실글 끝 idx
		
		public Page(int currentPage, int pageSize, int totalCount) {
			this.pageSize = pageSize;
			this.totalCount = totalCount;
			totalPage = (totalCount-1)/pageSize +1; //ex) (81-1)/(10+1)=8 > 정수/정수=정수
			
			//현재페이지가 계산한 토탈페이지보다 크거나 1보다 작으면 무조건 1이고 아니면 가져온 현재페이지로 진행
			//잘못 요청된 currentPage를 1~totalPage 범위값으로 설정해야 하므로 1로 초기화.
			this.currentPage = (currentPage>totalPage || currentPage < 1)? 1:currentPage; 
			startNo = (this.currentPage-1) * pageSize;
			
			//startPage, endPage 의 수식은 나중에 추가
			startPage = (this.currentPage - 1) / 10 * 10 + 1;   //currentPage 14일때 , 11 
			endPage = startPage + 9;							//currentPage 14일때 , 20
			endPage = endPage > totalPage ? totalPage : endPage;   //마지막 페이지목록에서 필요함.
		}
		
	    
} 
