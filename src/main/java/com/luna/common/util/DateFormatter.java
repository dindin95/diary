package com.luna.common.util;


import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.extern.log4j.Log4j;

@Log4j
public class DateFormatter {
//db에서 받아온 시간에 추가를 하는것

	static SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
	
	public static String parseToWords(Date date) {
		
		String resultStr = "";
		Date now = new Date();
		// 시차때문에 9 * 1000 * 60 * 60 씀
		// java.util.Date => 몇년몇월며칠몇초를 한꺼번에 출력할때
		java.util.Date utilDate = new java.util.Date(date.getTime() - 9 * 1000 * 60 * 60);
		// 현재시간이랑 db에 넣은시간이랑 시간차이
		Long result = now.getTime() - utilDate.getTime();

		// 분 확인 => 방금
		if (1 > (result / 1000 / 60)) {
			resultStr = "현재";
		} else if (1 > (result/1000/60/60)) {
			resultStr = result / 1000 / 60 + " 몇분전 ";
		} else if(1 > (result/1000/60/60/24)){
			resultStr = result / 1000 / 60 /60 + " 몇시간전 ";
		} else if (1 > (result / 1000 / 60 / 60 / 24 / 7)){
			resultStr = result/1000/60/60/24 + " 며칠전 ";
		} else {
			resultStr = formatter.format(utilDate);
		}
		
		
		log.info("==============================");
		log.info("결과 : " + resultStr);
		log.info("시간차이 : " + result / 1000 / 60 + "분");	
		log.info("현재시간 : " + now);
		log.info("등록시간 : " + utilDate);

		// 월 확인
		// 일 확인
		// 시간 확인
		// 분 확인
		// 방금

		return resultStr;
	}
	
	// date를 넣으면 string으로 바꿔줌
	public static String fromDateToString(Date date) {
			return  formatter.format(new java.util.Date(date.getTime() - 9 * 1000 * 60 * 60));
	}
	

	// string을 date로 바꿔줌
	public static Date fromStringToDate(String str) throws Exception {
		return null == str || str.length() == 0 ? null : formatter.parse(str);
	}
}
