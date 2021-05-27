package com.luna.diary.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplyVO {
	
	private Integer rno,dno;
	private String reply, replyer;
	private Date regDate, updateDate;

}

