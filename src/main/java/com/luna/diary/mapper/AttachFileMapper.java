package com.luna.diary.mapper;

import java.util.List;

import com.luna.diary.domain.AttachFileVO;

public interface AttachFileMapper {
	//첨부파일은 수정개념이 없음 게시글을 수정하면서 추가하거나 삭제할뿐
	
	void insert(AttachFileVO vo);
	
	void delete(String uuid);
	
	//게시물 삭제하면 첨부파일이 전체 삭제되어야되니깐
	void deleteAll(Integer dno);

	//게시글 번호로 첨부파일 찾는다
	//게시글에 해당되는 첨부파일을 가져와야되니깐 list
	List<AttachFileVO> findByDno(Integer dno);
	
	List<AttachFileVO> getOldFiles();
}
