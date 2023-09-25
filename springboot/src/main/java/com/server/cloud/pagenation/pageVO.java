package com.server.cloud.pagenation;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.Data;

@Data
public class pageVO {

	
	
	private int start;//시작 페이지 번호
	private int end;//끝 페이지 네이션
	private boolean prev;//이전 버튼 활성화 여부
	private boolean next;//다음 버튼 활성화 여부
	private int realEnd;//실제 보여지는 끝번호
	
	
	private Criteria cri; //페이징 기준
	private int pnCount=5;//페이지네이션 개수
	
	private int total;//전체 게시글 개수
	private int page;//cri에 있는 현재 조회하는 페이지
	private int amount;//cri에 있는 데이터 개수

	
	
	private List<Integer>pageList;
	
	
	public pageVO( Criteria cri,int total) {
		this.cri=cri;
		this.page=cri.getPage();
		this.amount=cri.getAmount();
		this.total=total;
		
		//end페이지계산 ->현재조회하는 페이지와 연관이 있다.
		//page넘버가 1을 가르킬 때, end=10
		//page가 11을 가르킬 땐 end=20
//		/this.end=(int)(Math.ceil(현재조회하는 페이지/페이지네이션개수))*페이지네이션개수;
		this.end=(int)(Math.ceil(this.page/(double)this.pnCount))* this.pnCount;//ex)page=11이면 1.1올림 ->2 *10 =20
		//start페이지 계산
		//this.start = this.end-페이지네이션개수+1;
		this.start = this.end-this.pnCount+1;
		
		//실제 끝번호의 계산 realEnd ->
		//전체 게시글 수와 연관이 있다 ex) 데이터 개수 10개 기준으로 ->총 게시글 수가 53개 라면 마지막 페이지 번호는 6 or 게시글 수가 232개면 페이지 번호는 24
		
//		this.realEnd=(int)(Math.ceil(전체게시글 수/(double)데이터 개수));
		this.realEnd=(int)(Math.ceil(this.total/(double)this.amount));

		
		//end페이지의 재결정
		//데이터가 25개 -> end=10, realEnd=3 일때 realEnd를 따라간다
		//데이터가 153개 -> end=20,realEnd=16 일때 실제 끝번호를 따라감
		//데이터가 153개 (3번 페이지 조회)->end=10,realEnd=16(페이지네이션을 따라감)
		
		if(this.end>this.realEnd) {
			this.end=this.realEnd;
		}
		//prev스타트 페이지버튼 활성화 여부
		//start페이지 결겅은1,11,21,31,41.... <-1에서만 false
		this.prev=this.start>1;
		
		//next활성화 여부
		//end=10,realEnd=16이라고 할 때 다음 버튼이 있다는 의미.
		this.next=this.realEnd>this.end;
		
		
		//타임리프-리스트에 페이지네이션을 담음
		this.pageList= IntStream.rangeClosed(this.start, this.end).boxed().collect(Collectors.toList());
		
		
		
	}
	
}
