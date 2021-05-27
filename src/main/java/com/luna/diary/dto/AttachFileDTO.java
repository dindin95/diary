package com.luna.diary.dto;

import java.util.List;

import com.luna.diary.domain.AttachFileVO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AttachFileDTO {
	
	private String fileName;
	//업로드 경로
	private String uploadPath;
	//파일 유효아이디
	private String uuid;
	//이미지 여부
	private Boolean fileType;
	
	private Integer dno;
	

	
	

}
