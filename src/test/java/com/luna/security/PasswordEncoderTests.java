package com.luna.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.luna.common.config.RootConfig;
import com.luna.security.config.SecurityConfig;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RootConfig.class,  SecurityConfig.class }) // 경로를 적어주면 컴피그로 등록돼서 실행
@Log4j
public class PasswordEncoderTests {
	
	@Autowired
	PasswordEncoder pwEndocd;
	
	@Test
	public void testEncode() {
		
		String str = "member";
		
		String enStr = pwEndocd.encode(str);
		
		log.info(enStr);
		//$2a$10$dunYl.Mp6CLNks7m8wSnGehTywXUTeCKxRlzevUnsqseBAUFotIC2
	}

}
