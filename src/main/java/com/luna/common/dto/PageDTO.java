package com.luna.common.dto;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageDTO {
	
	@Builder.Default
	private int page = 1;
	
	@Builder.Default
	private int perSheet = 10;
	
	private String type; // 제목 t 내용 c 
	
	private String keyword;
	
	//음수를 방지하기위해서 페이지의 내용물의 시작점(dno)
	// ex) ( 0-1 ) * 10 < 0 음수이니깐 ture -> 0 
	//     ( 2-1 ) * 10 < 0 은 10이니깐 false  (2-1)*10 으로 10페이지의 시작점 dno들
	public int getSkip() {
		return (page - 1) * perSheet < 0 ? 0 : (page - 1) * perSheet;
	}
	
	//trim은 공백 자르는거
	public String[] getArr() {
		
		if(keyword == null || keyword.trim().length() == 0) {
			return null; // --> if 값이 ture면 return null 이되므로 끝  return type.split("") 로 안감 리턴끝나면 땡
			
		}
		
		if(type == null) {
			return null;
		}
		
		//타입의 공백을 자른다  tw tcw 이런거 자르기
		return type.split("");
	}
	
	
	//UriComponentsBuilder->브라우저에서 get방식 등의 파라미터 전송에 사용되는 문자열을 손쉽게 처리
	public String getListLimk() {
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
				.queryParam("page", this.page)
				.queryParam("perSheet", this.perSheet)
				.queryParam("type", this.type)
				.queryParam("keyword", this.keyword);
		
		return builder.toUriString();
				
				
	}
}
