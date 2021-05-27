package com.luna.security;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.luna.common.config.RootConfig;
import com.luna.security.config.SecurityConfig;
import com.luna.security.domain.AuthVO;
import com.luna.security.domain.MemberVO;
import com.luna.security.mapper.MemberMapper;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RootConfig.class,  SecurityConfig.class }) // 경로를 적어주면 컴피그로 등록돼서 실행
@Log4j
public class MemberTests {
	
	@Autowired
	PasswordEncoder pwEndocd;
	
	@Autowired
	DataSource ds;
	
	@Autowired
	MemberMapper mapper;
	
	@Test
	public void testInsertMember() {
		
		String sql = "insert into tbl_member(userid, userpw, username) values (?,?,?)";
		
		for(int i = 0; i < 100; i++) {
			
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(2, pwEndocd.encode("pw" +i));
				
				if( i  < 80) {
					
					pstmt.setString(1, "user"+i);
					pstmt.setString(3, "일반사용자"+i);
					
				}else if (i < 90) {
					pstmt.setString(1, "manager"+i);
					pstmt.setString(3, "운영자"+i);
				}else {
					pstmt.setString(1, "admin"+i);
					pstmt.setString(3, "관리자"+i);
				}
				
				pstmt.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if(pstmt != null) {try {pstmt.close(); } catch(Exception e) {} }
				if(con != null) {try {con.close(); } catch(Exception e) {} }
			}
		}//end for
	}

	
	@Test
	public void testRead() {
		MemberVO vo = mapper.read("admin90");
		
		log.info(vo);
		
		vo.getAuthList().forEach(authVO -> log.info(authVO));
	}
	
}
