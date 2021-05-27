package com.luna.diary.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luna.common.dto.PageDTO;
import com.luna.common.dto.PageMaker;
import com.luna.diary.domain.ReplyVO;
import com.luna.diary.dto.ReplyDTO;
import com.luna.diary.service.ReplyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

//cors처리
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/replies/")
@RequiredArgsConstructor
@Log4j
public class ReplyController {
	
	private final ReplyService service;
	
	//보내는 방법 3가지
//	@RequestBody ReplyDTO dto,
//	@PathVariable(name="page") Integer page,
//	@RequestParam(name="perSheet") Integer perSheet)

	
	//댓글등록
	//댓글 등록이니깐 구분 짓고 싶어서 new를 씀
	@PostMapping(value ="/new" , produces = {MediaType.APPLICATION_JSON_VALUE})
	//내가 보낸거에 응답을 받기 위해서
	@PreAuthorize("isAuthenticated")
	public ResponseEntity<String> create( @RequestBody ReplyDTO dto){
		log.info("====reply create ======");
		
		service.insert(dto);
		
		
		log.info("=======댓글보냄=======");
		log.info("=======dto=======" + dto);
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
	
	// 댓글 목록 확인dno
	@GetMapping(value ="/pages/{dno}/{page}", produces={ MediaType.APPLICATION_JSON_VALUE })
	//응답을 키 값으로 받을것이다
	public ResponseEntity<Map<String,Object>> getReplyListPage(
			@PathVariable(name="dno") Integer dno,@PathVariable(name="page") Integer page){
		log.info("=======getReplyListPage======");
		log.info("=======dno======>>" + dno);
		log.info("=======page======>>" + page);
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		PageDTO pageDTO = PageDTO.builder().page(page).perSheet(10).build();
		PageMaker pageMaker = new PageMaker(pageDTO,service.getReplyCount(dno));
		List<ReplyDTO> replyList = service.replyList(dno,pageDTO);
		
		log.info("===count=====>" + service.getReplyCount(dno));
		log.info("===replyList=====>" + replyList);
		log.info("===pageDTO=====>" + pageDTO);
		log.info("===pageMaker=====>" + pageMaker);
	
		
		
		resultMap.put("replyList", replyList);
		resultMap.put("pageMaker", pageMaker);
		
     	return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
		
	}
	@PreAuthorize("principal.username == #vo.replyer")
	@DeleteMapping(value= "/{rno}" , produces={ MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> remove(@PathVariable("rno") Integer rno, @RequestBody ReplyDTO dto){
		
		log.info("=====reply remove==========");
		
		service.delete(rno);
		
		
		log.info("====rno=====" + rno);
		
		
		
		return new ResponseEntity<String>("success", HttpStatus.OK); 
	}
	@PreAuthorize("principal.username == #dto.replyer")
	@PutMapping(value= "/{rno}" , produces={ MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> update(@PathVariable("rno") Integer rno , @RequestBody ReplyDTO dto){
		
		log.info("=====reply update==========");
		

		log.info("====rno=====" + rno);
		
		service.update(dto);
		
		return new ResponseEntity<String>("success", HttpStatus.OK); 
	}

}
