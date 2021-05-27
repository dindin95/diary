package com.luna.diary.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.luna.common.dto.PageDTO;
import com.luna.common.dto.PageMaker;
import com.luna.diary.dto.AttachFileDTO;
import com.luna.diary.dto.DiaryDTO;
import com.luna.diary.service.DiaryService;
import com.luna.diary.service.ReplyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/diary")
@RequiredArgsConstructor
@Log4j
public class DiaryController {
	// ajax로 하면 데이터를 주고받는데 url변동없이 이용 비동기

	private final DiaryService diaryservice;
	private final ReplyService replyservice;

	@GetMapping("/list")
	@PreAuthorize("isAuthenticated()")
	public void getList(PageDTO page, Model model) {
		log.info("=============pageList==============");
		// 페이지 메이커에 페이지 기준이랑 토탈값
		PageMaker maker = new PageMaker(page, diaryservice.getPageCount(page));
		// list만들어야되니깐
		List<DiaryDTO> diraylist = diaryservice.getList(page);
		
	
		
		model.addAttribute("pageMaker", maker);
		model.addAttribute("list", diraylist);
		
		log.info(diraylist);

	}

	// 객체자료는 modal을 안에 가지고있음
	// post에는 page를 가지고잇는데 get에서는 page를 안가지고 있어서 오류 생김
	@GetMapping("/register")
	@PreAuthorize("isAuthenticated")
	public void getRegister(PageDTO pageDTO) {
		log.info("====== register get ======");

	}

	@PostMapping(value = "/register", produces = { "text/plain" })
	@ResponseBody
	@PreAuthorize("isAuthenticated")
	// ResponseEntity<String> 벨리데이션(검증) -> 이 서버가 제대로 동작했다는걸 알려주는거
	public ResponseEntity<String> postRegister(PageDTO pageDTO, @RequestBody DiaryDTO diaryDTO ) {
		log.info("=============start Register post==============");

		log.info("controller dto " +diaryDTO);

		diaryservice.register(diaryDTO);
		
		

		log.info("=======Register post========");
		return new ResponseEntity("success", HttpStatus.OK);

	}

	@GetMapping("/view")
	@PreAuthorize("isAuthenticated")
	public void getView(PageDTO pageDTO, DiaryDTO diaryDTO, Model model) {
		log.info("=====view=====");

		Integer dno = diaryDTO.getDno();
		log.info("=======dno==========" + dno);

		model.addAttribute("pageDTO", pageDTO);
		model.addAttribute("diaryDTO", diaryservice.selectOne(dno));
		
		DiaryDTO dto = new DiaryDTO();
		
		log.info("--- view ----" + dto);
		log.info("----diaryDTO----"+diaryDTO.getTitle());

	}
	
	
	@RequestMapping(value = "/delete", produces = {"text/plain"})
	@ResponseBody
	@PreAuthorize("principal.username == #writer")
	public ResponseEntity<String> posteDelete(@RequestBody DiaryDTO diaryDTO) {
		log.info("=============view delete==============");
		
		//delete할때 dto의 dno가 필요하니까
		diaryservice.delete(diaryDTO.getDno());
		
		log.info("===========delete========");
 
		return new ResponseEntity("success", HttpStatus.OK);

	}
	
	@GetMapping("/modify")
	public void getModify(PageDTO pageDTO, DiaryDTO diaryDTO, Model model) {
		log.info("=====modify=====");

		Integer dno = diaryDTO.getDno();
		log.info("=======dno==========" + dno);

		model.addAttribute("pageDTO", pageDTO);
		model.addAttribute("diaryDTO", diaryservice.selectOne(dno));

	}

	@RequestMapping(value = "/modify", produces = {"text/plain"})
	@ResponseBody
	@PreAuthorize("principal.username == #diary.writer")
	public ResponseEntity<String> postModify(PageDTO pageDTO, @RequestBody DiaryDTO diaryDTO) {
		log.info("=====modify post=====");

		diaryservice.modify(diaryDTO);
		log.info("=======modify==========" + diaryDTO);


		return new ResponseEntity("success", HttpStatus.OK);

	}
	
	@GetMapping(value = "/getAttachList", produces =MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<AttachFileDTO>>getAttachList(Integer dno){
		
		log.info("== getAttachList =="+dno);
		
		return new ResponseEntity(diaryservice.getFileList(dno), HttpStatus.OK);
	}
}
