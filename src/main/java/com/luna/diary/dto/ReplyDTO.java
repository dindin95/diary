package com.luna.diary.dto;

import java.sql.Date;

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
public class ReplyDTO {
	
	private Integer rno,dno;
	private String reply, replyer;
	private Date regDate, updateDate;
	//댓글 며칠전에 달림 이런거 할려고
	//DB에서 가져올 필요가 없으니깐 VO에는 안씀
	private String updateDateStr;
	
	

}

