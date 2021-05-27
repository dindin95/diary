package com.luna.diary;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.luna.common.config.RootConfig;
import com.luna.common.dto.PageDTO;
import com.luna.diary.config.DiaryConfig;
import com.luna.diary.dto.DiaryDTO;
import com.luna.diary.service.DiaryService;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RootConfig.class, DiaryConfig.class }) // 경로를 적어주면 컴피그로 등록돼서 실행
@Log4j
public class DiaryServiceTests {
	
	@Autowired
	DiaryService service;
	
	@Test
	public void testSelectOneTest() {
		log.info("service ---> " + service);
		log.info("Diary : " + service.selectOne(1094));
	}
	
	@Test
	public void registerTest() {
		DiaryDTO dto = DiaryDTO.builder().title("커피는").content("콜드브루지").writer("스벅예").build();
		service.register(dto);
		
		log.info("-------registerTest------------" + service.selectOne(252));
	}
	
	@Test
	public void deleteTest() {
		service.delete(182);
		
		log.info("--------delete------ ") ;
	}

	@Test
	public void modifyTest() {
		DiaryDTO dto = DiaryDTO.builder().dno(181).title("커피마셔도").content("졸려").writer("큰일이닷").build();
		service.modify(dto);
		
		log.info("--------modifyTest------ "+ service.selectOne(181)) ;
	}
	
	@Test
	public void getPageCountTest() {
		
		PageDTO page = new PageDTO();
		service.getPageCount(page);
		
		log.info("=============getPageCountTest=============>>>"+ service.getPageCount(page)) ;
	}
	
	@Test
	public void getListTest() {
		
		PageDTO page = new PageDTO();
		page.setKeyword("");
		page.setType("");
		page.setPage(1);
		page.setPerSheet(10);
		
		
		
		log.info("=============getListTest=============>>>"+ service.getList(page)) ;
	}
}
