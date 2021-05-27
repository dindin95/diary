package com.luna.diary.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luna.common.dto.PageDTO;
import com.luna.diary.domain.ReplyVO;
import com.luna.diary.dto.ReplyDTO;
import com.luna.diary.mapper.DiaryMapper;
import com.luna.diary.mapper.ReplyMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;


@Service
@RequiredArgsConstructor
@Log4j
public class ReplyServiceImp implements ReplyService{
//사용자가 입력 insert delete update니깐 DB(vo)로 넘겨줘야됨
	
	
	
	private final ReplyMapper mapper;
	private final DiaryMapper dm;

	@Transactional
	@Override
	public void insert(ReplyDTO replyDTO) {
			
		log.info("----reply insert------->" + replyDTO);
		dm.updateReplyCount(replyDTO.getDno(), 1);
		
		mapper.insert(toDomain(replyDTO));
		
	}
	
	@Transactional
	@Override
	public int delete(Integer rno) {
		
		ReplyDTO replyDTO = new ReplyDTO();
		
		
		
		mapper.delete(rno);
		//삭제하고 끝이니깐 db에서 가져올 필요가 없음
	//	dm.updateReplyCount(replyDTO.getRno(),-1);
		
		return dm.updateReplyCount(replyDTO.getRno(),-1);
		

	}
	
	

	@Override
	public ReplyDTO selectOne(Integer rno) {
		//사용자가 클릭한것을 (DB)가져와야되니깐 vo -> dto로 치환시켜야됨
		log.info("----reply select----->" + mapper.selectOne(rno));
		return toDTO(mapper.selectOne(rno));
	}



	@Override
	public void update(ReplyDTO replyDTO) {
		//DB로 넘겨주는거니깐 vo로 치환시켜야됨
		mapper.update(toDomain(replyDTO));
		
	}

	@Override
	public List<ReplyDTO> replyList(Integer dno, PageDTO page) {
		
		return toDTOList(mapper.replyList(dno,  page.getSkip()));
	}

	@Override
	public int getReplyCount(Integer dno) {
		
		
		return mapper.getReplyCount(dno);


	}

}
