package com.luna.diary.domain;

import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiaryVO {
	
	private Integer dno;
	private String title, content, writer;
	private Date regdate, updatedate;
	private Integer replyCount;
	
	private List<AttachFileVO> attachList;

}

