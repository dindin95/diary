package com.luna.crawler.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages =  "com.luna.crawler.service")
@MapperScan(basePackages = "com.luna.diary.mapper")
@EnableAspectJAutoProxy
@EnableTransactionManagement
@EnableScheduling
public class CrawlerConfig {

	{
		System.out.println("---------------------------------------------");

		System.out.println("---------------------------------------------");

		System.out.println("---------------------------------------------");
		
		
	}
}
