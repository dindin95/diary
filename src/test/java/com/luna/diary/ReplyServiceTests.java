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
import com.luna.diary.dto.ReplyDTO;
import com.luna.diary.mapper.ReplyMapper;
import com.luna.diary.service.ReplyService;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RootConfig.class, DiaryConfig.class }) // 경로를 적어주면 컴피그로 등록돼서 실행
@Log4j
public class ReplyServiceTests {
	
	@Autowired
	ReplyService service;
	
	@Autowired
	ReplyMapper mapper;
	
	@Test
	public void testSelectOneTest() {
		log.info("Diary : " + service.selectOne(1));
	}
	
	@Test
	public void insertTest() {
		ReplyDTO dto =  ReplyDTO.builder().dno(1095).reply("점심맛남").replyer("익명").build();
		
		service.insert(dto);
			
		log.info("-------insert Test------------" + mapper.selectOne(30));
	}
	
	@Test
	public void deleteTest() {
		service.delete(30);
		
		log.info("--------delete------ ") ;
	}

	@Test
	public void updateTest() {
		//댓글수정이니깐 dno를 가져올 필요가 없음 rno를 가져오면 알아서 가져와지니깐
		ReplyDTO dto =  ReplyDTO.builder().rno(29).reply("얼른하자").build();
		
		service.update(dto);
		
		log.info("--------update----------"+ service.selectOne(29));
		
	
	}
	

	@Test
	public void getListTest() {
		
		PageDTO page = new PageDTO();
		
		
		Integer dno = 1188;
		
		
		int skip = page.getSkip();
		
		
		List<ReplyDTO> list = service.replyList(dno,page);
		
		list.forEach(r->log.info(r));
		
		log.info("--------list----------"+ list);
		
		
		log.info(service.getReplyCount(dno));
		
	
	}
	

	@Test
	public void getCountTest() {
		
	


		
		int dno = 1188;
		
		log.info(service.getReplyCount(dno));
		
	
	}
	
	
}
