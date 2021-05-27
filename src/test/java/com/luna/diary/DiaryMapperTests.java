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
import com.luna.diary.domain.AttachFileVO;
import com.luna.diary.domain.DiaryVO;
import com.luna.diary.mapper.AttachFileMapper;
import com.luna.diary.mapper.DiaryMapper;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RootConfig.class, DiaryConfig.class }) // 경로를 적어주면 컴피그로 등록돼서 실행
@Log4j
public class DiaryMapperTests {

	@Autowired
	DiaryMapper mapper;
	
	@Autowired
	AttachFileMapper file;

	@Test
	public void selectOneTest() {

		log.info("----selectOne---" + mapper.selectOne(138));
	}

	@Test
	public void registerTest() {
		DiaryVO vo = DiaryVO.builder().
				title("점심시간").
				content("이예ㅖㅖㅖ").
				writer("배고픈자").
				build();
  
	           mapper.register(vo);
		log.info("----insert-----" + vo.getDno());
	}
	
	
	@Test
	public void  deleteTest() {
		
		mapper.delete(185);
		log.info("----delete----" + mapper.selectOne(185));
	}
	
	@Test
	public void updateTest() {
		DiaryVO vo = DiaryVO.builder().
				dno(183).
				title("수정이다").
				content("오늘은 제대로하자").
				writer("화이티").
				build();
		
        mapper.modify(vo);
        
	log.info("----modify-----" + mapper.selectOne(183));
	}
	
	
	@Test
	public void pageCountTest() {
		
		PageDTO pageDTO = new PageDTO();
		
		mapper.getPageCount(pageDTO);
		
		log.info("----pageCountTest-----" + mapper.getPageCount(pageDTO));
		
	}
	
	@Test
	public void pageListTest() {
		
	
		
		PageDTO page = new PageDTO();
		page.setKeyword("");
		page.setType("");
		page.setPage(1);
		page.setPerSheet(10);
		
		mapper.getList(page);
		
//		log.info("----pageCountTest-----" +mapper.getList(page));
		
		//보기 편하게 루프돌려서 확인
		//루프돌리기
		List<DiaryVO> list = mapper.getList(page);
		//list 안의 내용을 d라고 정의해서 루프 돌림
		list.forEach(d -> log.info(d));
		log.info(list);
		
	
		
	}
	
	@Test
	public void updateReplyCountTest() {
		
		Integer dno = 1095;
		
		mapper.updateReplyCount(dno, 1);
		
		log.info("updateCount000>"+mapper.selectOne(dno));
	}
	
	

	@Test
	public void registerTest2() {
		DiaryVO vo = DiaryVO.builder().
				title("점심시간").
				content("이예ㅖㅖㅖ").
				writer("배고픈자").
				build();
  
	           mapper.register(vo);
	           
	        // AttachFileVO f = AttachFileVO.builder().dno(vo.getDno()).build();
	        
	       
	        
	       
		log.info("----insert-----" + vo.getDno());
	}
	

}
