package com.luna.diary.dto;

import java.sql.Date;
import java.util.List;

import com.luna.diary.domain.AttachFileVO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiaryDTO {
	
	private Integer dno;
	private String title, content, writer;
	private Date regdate, updatedate;

	private Integer replyCount;
	
	private List<AttachFileDTO> attachList;

}

