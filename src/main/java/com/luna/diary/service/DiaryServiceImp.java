package com.luna.diary.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luna.common.dto.PageDTO;
import com.luna.diary.domain.DiaryVO;
import com.luna.diary.dto.AttachFileDTO;
import com.luna.diary.dto.DiaryDTO;
import com.luna.diary.mapper.AttachFileMapper;
import com.luna.diary.mapper.DiaryMapper;
import com.luna.diary.mapper.ReplyMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@RequiredArgsConstructor
@Log4j
public class DiaryServiceImp implements DiaryService {
	
	private final DiaryMapper diaryMapper;
	private final  AttachFileMapper fileMapper;
	private final ReplyMapper repMapper;
	
	@Override
	public DiaryDTO selectOne(Integer dno) {
		//사용자가 클릭한것을 가져와야되니깐 vo -> dto로 치환시켜야됨
		//return diaryMapper.selectOne(toDTO(dno)) 안되는 이유는 
		//toDTO가 vo여야되는데  파라미터가 dno여서
		return toDTO(diaryMapper.selectOne(dno));
		
	}



	@Transactional
	@Override
	public void register(DiaryDTO diaryDTO) {
		
		DiaryVO diary = toDomain(diaryDTO);
		
		diaryMapper.register(diary);
		
	//vo의 dno
	Integer dtoDno = diary.getDno();
	
	diaryDTO.setDno(dtoDno);
	log.info("diaryDTO----->"+diaryDTO);
	
	AttachFileDTO fileDTO = new AttachFileDTO();
	
	fileDTO.setDno(dtoDno);
	
	log.info("file fileDTO--->"+fileDTO);
	
	log.info("file--->"+diaryDTO);

	  diaryDTO.getAttachList().forEach(file -> {		
		file.setDno(dtoDno);
	});
	  
	  log.info("---1 --->" +diaryDTO.getAttachList());
	  
	  fileVO(diaryDTO.getAttachList()).forEach(list -> {
		  
		  fileMapper.insert(list);
	  });
	 
	  log.info("---2 --->" +fileVO(diaryDTO.getAttachList()));	
	}


	@Transactional
	@Override
	public void delete(int dno) {
		//삭제하고 가져올게 반환할게 없으니깐 vo dto를 안써도됨
		//외래키먼저 삭제해야되니깐 file먼저 삭제하고 diary를 삭제해야됨
		fileMapper.deleteAll(dno);
		diaryMapper.delete(dno);
		
		
	}

	@Transactional
	@Override
	public void modify(DiaryDTO diaryDTO) {
		
		DiaryVO diary = toDomain(diaryDTO);
		
		
		
		Integer dtoDno = diary.getDno();
		
		fileMapper.deleteAll(dtoDno);
		
		diaryMapper.modify(diary);
		
		  diaryDTO.getAttachList().forEach(file -> {		
				file.setDno(dtoDno);
			});
			  
			  log.info("---1 --->" +diaryDTO.getAttachList());
			  
			  fileVO(diaryDTO.getAttachList()).forEach(list -> {
				  
				  fileMapper.insert(list);
				  log.info("----list---->"+list);
			  });
			 
			  log.info("---2 --->" +fileVO(diaryDTO.getAttachList()));	
		
		

		
	}

	@Override
	public Integer getPageCount(PageDTO pageDTO) {
		return diaryMapper.getPageCount(pageDTO);
	}

	


	@Override
	public List<DiaryDTO> getList(PageDTO pageDTO) {
		
		DiaryDTO dto = new DiaryDTO();
		
		return toDTOList(diaryMapper.getList(pageDTO));
	}


	@Override
	public List<AttachFileDTO> getFileList(Integer dno) {
		log.info(" === get dno ===" + dno);
		
		fileMapper.findByDno(dno);
		
		return fileDTO(fileMapper.findByDno(dno));
	}


//	@Override	
//	public void insert(AttachFileDTO fileDTO) {
//		// TODO Auto-generated method stub
//		
//	}


	

}
