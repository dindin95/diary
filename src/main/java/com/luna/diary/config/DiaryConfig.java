package com.luna.diary.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@MapperScan(basePackages = "com.luna.diary.mapper")
@ComponentScan(basePackages = {"com.luna.diary.service", "com.luna.common.util"})
@EnableAspectJAutoProxy
@EnableTransactionManagement
@EnableScheduling
public class DiaryConfig {

	{
		System.out.println("-------------------------------------------");
	}
}
