package com.luna.diary;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.luna.common.config.RootConfig;
import com.luna.common.dto.PageDTO;
import com.luna.diary.config.DiaryConfig;
import com.luna.diary.domain.ReplyVO;
import com.luna.diary.mapper.ReplyMapper;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RootConfig.class, DiaryConfig.class }) // 경로를 적어주면 컴피그로 등록돼서 실행
@Log4j
public class ReplyMapperTests {
	
	
	@Autowired
	ReplyMapper mapper;
	
	
	@Test
	public void insertTest() {
	    // rno,dno, reply, replyer, regDate, updateDate;
	 // ReplyVO vo = new ReplyVO(null,null,"배고파",null,null,null); 생성자주입 무조건 순서대로 넣어야됨
		
		//임의의 게시글 번호 가져와야됨
		ReplyVO vo = ReplyVO.builder().dno(1095).reply("힝힝").replyer("익명").build();
		
		mapper.insert(vo);
		
		log.info("---댓글등록---");
		
		
	}
	
	@Test
	public void selectOneTest() {
		
	
		log.info("====댓글조회==== " + mapper.selectOne(2) );
	}


	@Test
	public void deleteTest() {
		
		 mapper.delete(2);
		
		log.info("======댓글삭제========= ");
		log.info("====댓글조회==== " + mapper.selectOne(2) );
	}
	
	@Test
	public void updateTest() {
		
		ReplyVO vo = ReplyVO.builder().rno(1).reply("헉!!").build();
		
		mapper.update(vo);
		
		log.info("====댓글조회==== " + mapper.selectOne(1) );
		
	}
	
	@Test
	public void listTest() {
		
	//	List<ReplyVO> list = new List<ReplyVO>(); new는 list(추상클래스)가 안됨
		
		ReplyVO vo = new ReplyVO();
		
		Integer dno = 1095;
		int skip = 0;
		
		//루프 돌려서 댓글 보기
		List<ReplyVO> list = mapper.replyList(dno, skip);
		
		list.forEach(d -> log.info(d));
		log.info(list);
	}
	
	@Test
	public void countTest() {
		
		ReplyVO vo = ReplyVO.builder().dno(1095).build();
		
		
		

		//mapper.getReplyCount(vo);
		
		//log.info(mapper.getReplyCount(vo));
	}
}
