package com.luna.diary.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.luna.common.dto.PageDTO;
import com.luna.diary.domain.DiaryVO;

public interface DiaryMapper {
	
	//게시글 하나 조회 
   DiaryVO selectOne(Integer dno);
   
   //게시글 등록
   void register(DiaryVO diary);
   
   //게시글 삭제
   void delete(int dno);
   
   //게시글 수정
   void modify(DiaryVO diary);
   
   //전체 페이지  -> pageNation을 얼만큼 할건지 ex) 13까지인지 18페이지까지인지
   int getPageCount(PageDTO pageDTO);
   
   //페이지 리스트 페이지에 dno를 얼만큼 넣을건지
   List<DiaryVO> getList(PageDTO pageDTO);
   
   //댓글 트랜잭션
   public int updateReplyCount(@Param("dno") Integer dno, @Param("amount") int amount);
   
   

}
