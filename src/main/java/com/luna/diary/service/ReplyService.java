package com.luna.diary.service;

import java.util.List;
import java.util.stream.Collectors;

import com.luna.common.dto.PageDTO;
import com.luna.common.util.DateFormatter;
import com.luna.diary.domain.ReplyVO;
import com.luna.diary.dto.ReplyDTO;

public interface ReplyService {
//mapper는 vo service는 dto 그리고 서로 치환 시켜주는 곳
// 사용자가 본것을 DB로 넘기는 곳
//VO -> DTO () DTO -> VO
	
	
	   //댓글등록
	   void insert(ReplyDTO replyDTO);
	   
	   //댓글 조회
	   ReplyDTO selectOne(Integer rno);
	  
	   //댓글 삭제 삭제하고 rno를 가져와야돼서 return이 있어야됨
	   int delete(Integer rno) ;

	   //댓글 수정
	   void update(ReplyDTO replyDTO);
	   
	   //댓글 갯수
	   // dno를 가져와야 되니깐
	   int getReplyCount(Integer dno);
 
	   //mapper가 아니니깐 @param을 쓰면안됨
	   //1~10개까지 가져옴
	   List<ReplyDTO> replyList(Integer dno, PageDTO page);

	   //vo->dto
	   default ReplyDTO toDTO(ReplyVO vo) {
		   ReplyDTO replyDTO = new ReplyDTO();
		   replyDTO.setDno(vo.getDno());
		   replyDTO.setRno(vo.getRno());
		   replyDTO.setReply(vo.getReply());
		   replyDTO.setReplyer(vo.getReplyer());
		   replyDTO.setRegDate(vo.getRegDate());
		   replyDTO.setUpdateDate(vo.getUpdateDate());
		   //업데이트 된 시간을 가지고 와서 데이터추가
		   replyDTO.setUpdateDateStr(DateFormatter.fromDateToString(vo.getUpdateDate()));
		   
		   return replyDTO;
	   }
	   
	   // dto->vo
	   default ReplyVO toDomain(ReplyDTO dto) {
		   return ReplyVO.builder().
				   dno(dto.getDno())
				   .rno(dto.getRno())
				   .reply(dto.getReply())
				   .replyer(dto.getReplyer())
				   .regDate(dto.getRegDate())
				   .updateDate(dto.getUpdateDate()).build();
	   }
	   
	   //vo로 가져온 리스트를 dto 
	   default List<ReplyDTO> toDTOList(List<ReplyVO> replyList){
		   //vo의 내역 하나하나를 빨아야되니깐 steam으로
		   //mapping시켜주는거임 map은
		   //vo를 r로 임의로 지정해준거 그거를 collect로 묶어준거임
		   return replyList.stream().map(r ->toDTO(r)).collect(Collectors.toList());
	   }
}
