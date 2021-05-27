package com.luna.common.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.log4j.Log4j;

@Configuration
@EnableAspectJAutoProxy
@EnableTransactionManagement
@EnableScheduling
@Log4j
public class RootConfig {

	@Bean
	public String test() {
		log.info("================== CommonConfig started ==================");
		return "Common Config Test";
	}

	@Bean
	public DataSource dataSource() {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
		hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/dclass?serverTimezone=UTC");

		hikariConfig.setUsername("kim0303");
		hikariConfig.setPassword("kim0303");

		hikariConfig.setMinimumIdle(5);
		// test Query
		hikariConfig.setConnectionTestQuery("select now()");
		hikariConfig.setPoolName("springHikariCP");

		hikariConfig.addDataSourceProperty("dataSource.cachePrepStmts", "true");
		hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSize", "200");
		hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSqlLimit", "2048");
		hikariConfig.addDataSourceProperty("dataSource.useServerPrepStmts", "true");

		HikariDataSource dataSource = new HikariDataSource(hikariConfig);

		return dataSource;
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		// DataSource �꽕�젙
		sqlSessionFactory.setDataSource(dataSource());
		// mybatis-config �꽕�젙
//		Resource confiigLocation = new PathMatchingResourcePatternResolver()
//			.getResource("classpath:mybatis-config.xml");
//		sqlSessionFactory.setConfigLocation(confiigLocation);

		log.info("----sqlSessionFactory----");
		return (SqlSessionFactory) sqlSessionFactory.getObject();
		
	}
	
	@Bean(name= "txManager") 
	public PlatformTransactionManager txManager(@Qualifier("dataSource") DataSource dataSource) 
    { 
		DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(dataSource);
		dataSourceTransactionManager.setNestedTransactionAllowed(true); // nested return dataSourceTransactionManager; }
		log.info("txManager");
		return dataSourceTransactionManager;
    }

}
