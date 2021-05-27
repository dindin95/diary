package com.luna.diary.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.luna.diary.dto.AttachFileDTO;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

@Controller
@Log4j
public class UploadController {

	// 폴더생성
	private String getFloder() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Date date = new Date();

		String str = sdf.format(date);

		return str.replace("-", File.separator);

	}

	// 이미지파일 검사
	private boolean checkImageType(File file) {

		try {
			// 마인타입
			String contentType = Files.probeContentType(file.toPath());

			// 대상 문자열이 특정 문자 또는 문자열로 시작하는지 체크하는 함수
			return contentType.startsWith("image");

		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;

	}

	@GetMapping("/upload")
	public void upload() {

		log.info("upload");

	}

	@GetMapping("/uploadAjax")
	public void uploadAjax() {
		log.info("======= get upload Ajax ==========");
	}


	@PostMapping(value = "/uploadAjax", produces= MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<AttachFileDTO>> uploadAjax(MultipartFile[] uploadFile){
		
		log.info("=======post uploadAjax=======");
		log.info("======= uploadFile =======" + uploadFile);
		
		List<AttachFileDTO> list = new ArrayList<>();
		//파일폴더
		String uploadFolder = "C:\\upload";
		
		//폴더 저장경로
		String  uploadFolderPath = getFloder();
		
		//파일 저장 폴더+경로 
		File uploadPath = new File(uploadFolder, uploadFolderPath);
		
		if(uploadPath.exists() == false) {
			
			uploadPath.mkdirs();
		}// yyyy/mm/dd 폴더 만들기
		
		for(MultipartFile multipartFile : uploadFile) {
			
			AttachFileDTO dto = new AttachFileDTO();
			
			String uploadFileName = multipartFile.getOriginalFilename();
			
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);

			log.info("===== only file name ====" +uploadFileName );
			dto.setFileName(uploadFileName);
			
			UUID uuid = UUID.randomUUID();
			
			uploadFileName = uuid.toString() + "_" + uploadFileName;
			
			try {
				//저파일
				File saveFile = new File(uploadPath, uploadFileName);
				multipartFile.transferTo(saveFile);
				
				dto.setUuid(uuid.toString());
				dto.setUploadPath(uploadFolderPath);
			
			 //이미지 파일 체크
				if(checkImageType(saveFile)) {
					
					dto.setFileType(true);
					
					log.info(dto.getFileType());
					
					FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_" + uploadFileName));
					
					Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail,200,200);
					
					thumbnail.close();
				}
				
				//add to List
				list.add(dto);
				
				log.info("=== list ===" + list);
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}//end for
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	//사진 업로드하면 업로드 한거 보기
	@GetMapping("/display")
	@ResponseBody
	public ResponseEntity<byte[]> getFile(String fileName){
		
		log.info("=== display ===" );
		log.info("===fileName====" + fileName);
		
		File file = new File("c:\\upload\\" + fileName);
		
		log.info("====file======" + file);
		
		ResponseEntity<byte[]> result = null;
		
		try{
			
			HttpHeaders header = new HttpHeaders();
			
			header.add("Content-type", Files.probeContentType(file.toPath()));
			
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file),header, HttpStatus.OK);
			
		}catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	@GetMapping(value = "/download", produces= MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public ResponseEntity<Resource> downloadFile(@RequestHeader("User-Agent") String userAgent, String fileName){
		
		log.info("==== fileName ====" + fileName);
		
		
		Resource res = new FileSystemResource("c:\\upload\\" + fileName);
		
		if(res.exists() == false) {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		log.info("=== Resource ==="+res);
		
		String resName = res.getFilename();
		
		//remove uuid
		String resourcegetOriginalName = resName.substring(resName.indexOf("_")+1);
		
		HttpHeaders headers = new HttpHeaders();
		
		try {
			String downloadName = null;
			
			if(userAgent.contains("Trident")) {
				log.info("IE browser");
				downloadName = URLEncoder.encode(resourcegetOriginalName,"UTF-8").replaceAll("\\", " ");
			}else if(userAgent.contains("Edge")) {
				log.info("Edge");
				downloadName = URLEncoder.encode(resourcegetOriginalName,"UTF-8");
				
			}else {
				log.info("Chrome browser");
				downloadName = new String (resourcegetOriginalName.getBytes("UTF-8"),"ISO-8859-1");
			}
			log.info("downloadName: " +  downloadName);
			headers.add("Content-Disposition", "attachment; filename=" + downloadName);
			
		}catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<Resource>(res, headers, HttpStatus.OK);
		
	}
	
	
	@PostMapping(value = "/deleteFile", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteFile( String fileName,String fileType){
		log.info("delete  fileName-->" + fileName );
		log.info("delete type -->" +fileType );

		File file;
		
		try {
			file = new File("c:\\upload\\" + URLDecoder.decode(fileName,"UTF-8"));
			file.delete();
			
			if(fileType.equals("fileType")) {
				
				String largeFileName = file.getAbsolutePath().replace("s_", "");
				
				log.info(" largeFileName ---> " + largeFileName);
				
				file = new File(largeFileName);
				
				file.delete();
			}
		}catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}
}
