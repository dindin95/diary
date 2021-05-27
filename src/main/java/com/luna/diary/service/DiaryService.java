package com.luna.diary.service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.luna.common.dto.PageDTO;
import com.luna.diary.domain.AttachFileVO;
import com.luna.diary.domain.DiaryVO;
import com.luna.diary.dto.AttachFileDTO;
import com.luna.diary.dto.DiaryDTO;

public interface DiaryService {
// 사용자가 본것을 DB로 넘기는 곳
//VO -> DTO () DTO -> VO
 
	   //게시글 하나 조회 
	   DiaryDTO selectOne(Integer dno);
	   
	   //게시글 등록
	   void register(DiaryDTO diaryDTO);
	   
	   //게시글 삭제
	   void delete(int dno);
	   
	   //게시글 수정
	   void modify(DiaryDTO diaryDTO);
	   
	   //전체 페이지  -> pageNation을 얼만큼 할건지 ex) 13까지인지 18페이지까지인지
	   Integer getPageCount(PageDTO pageDTO);
	   
	   //페이지 리스트 페이지에 dno를 얼만큼 넣을건지
	   List<DiaryDTO> getList(PageDTO pageDTO);
	   
	   //upload 등록
	   List<AttachFileDTO> getFileList(Integer dno);
	   
//	   void insert(AttachFileDTO fileDTO);
	  
	   
		//dto를 vo로 변환
		//원래 서비스에는 껍데기만 있고 impld에서 속을 채워주는데 default를 하면 자체로 속이 채워짐
	   default DiaryVO toDomain(DiaryDTO dto) {

				   DiaryVO diaryVO = DiaryVO.builder().
				   dno(dto.getDno()).
				   title(dto.getTitle()).
		           writer(dto.getWriter()).
				   content(dto.getContent()).
				   regdate(dto.getRegdate()).
				   replyCount(dto.getReplyCount()).
				   updatedate(dto.getUpdatedate()).
				   build();
		   // vo는 set을 못쓰고 만약 builder까지 안했을때
//		   String title = dto.getTitle();
//		   String content = dto.getContent();
//		   String writer = dto.getWriter();
//		   Date regdate = dto.getRegdate();
//		   Date updatedate = dto.getUpdatedate();
//		   Integer dno = dto.getDno();
//		   Integer replyCount = dto.getReplyCount();		
		return diaryVO;
				//new DiaryVO(dno,title,content,writer,regdate,updatedate,replyCount,attachList);
		  
	   }
	   
	   //vo를 dto로 반환
	   default DiaryDTO toDTO(DiaryVO vo) {
//		   return DiaryDTO.builder().
//				   dno(vo.getDno()).
//				   title(vo.getTitle()).
//				   content(vo.getContent()).
//				   regdate(vo.getRegdate()).
//				   updatedate(vo.getUpdatedate()).
//				   build();
		   
		   //builder로 안하서 setter주입
		   DiaryDTO  diaryDto = new DiaryDTO();
		   diaryDto.setDno(vo.getDno());
		   
		   diaryDto.setTitle(vo.getTitle());
		   diaryDto.setContent(vo.getContent());
		   diaryDto.setWriter(vo.getWriter());
		   diaryDto.setRegdate(vo.getRegdate());
		   diaryDto.setReplyCount(vo.getReplyCount());
		   diaryDto.setUpdatedate(vo.getUpdatedate());

		   return diaryDto;

				  
	   }
	   
	   
	   //list도 vo로 만들었으니깐 dto로 
	   //list안의 내역들을 하나하나 dto로 바꾸는것
	   default List<DiaryDTO> toDTOList(List<DiaryVO> list){
		   return list.stream().map(diary ->{
			   return toDTO(diary);
		   }).collect(Collectors.toList());
		}	
	   
	   
	   default List<AttachFileDTO> fileDTO(List<AttachFileVO> list){
		   return list.stream().map(file -> {
			   AttachFileDTO dto = new AttachFileDTO();
			   dto.setDno(file.getDno());
			   dto.setFileName(file.getFileName());
			   dto.setFileType(file.getFileType());
			   dto.setUploadPath(file.getUploadPath());
			   dto.setUuid(file.getUuid());
			 return dto;
		   }).collect(Collectors.toList());
	   }
	   
	   default List<AttachFileVO> fileVO(List<AttachFileDTO> list){
		   return list.stream().map(file -> {
			   AttachFileVO vo =  AttachFileVO.builder()
					   .dno(file.getDno())
					   .fileName(file.getFileName())
					   .uploadPath(file.getUploadPath())
					   .uuid(file.getUuid())
					   .fileType(file.getFileType())
					   .build();
			return vo;
		   }).collect(Collectors.toList());
	   }
	   
	   
}
