package com.luna.security.mapper;

import com.luna.security.domain.MemberVO;

public interface MemberMapper {
	
	MemberVO read(String userid);

}
