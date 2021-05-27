package com.luna.diary.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttachFileVO {
	
	private String fileName;
	//업로드 경로
	private String uploadPath;
	//파일 유효아이디
	private String uuid;
	//이미지 여부
	private Boolean fileType;
	
	private Integer dno;
	
	

}
