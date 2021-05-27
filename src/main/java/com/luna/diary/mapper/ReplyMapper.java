package com.luna.diary.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.luna.common.dto.PageDTO;
import com.luna.diary.domain.ReplyVO;

public interface ReplyMapper {

	// 댓글등록
	void insert(ReplyVO vo);

	// 게시글 조회
	ReplyVO selectOne(Integer rno);

	// 게시글 삭제
	int delete(Integer rno);

	// 게시글 수정
	void update(ReplyVO vo);

	// 댓글 페이징
    // bno가 pk가 아니기 때문에 FullTableScan한다. 
	// 따라서 인덱스 생성이 필요하다 -> Non-Unique Key Lookup
	// 한페이지당 10개 어떤 게시물의 페이지번호 이용
	//댓글은 게시글 안에 있으니깐 dno기준으로

	List<ReplyVO> replyList(@Param("dno")Integer dno, @Param("skip") int skip);
	
	//댓글 전체 수
	int getReplyCount(@Param("dno")Integer dno);

}
