package com.luna.security.config;

import javax.sql.DataSource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.luna.security.custom.CustomAccessDeniedHandler;
import com.luna.security.custom.CustomLoginAccessHandler;
import com.luna.security.service.CustomUserDetailsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@MapperScan(basePackages = "com.luna.security.mapper")
@ComponentScan(basePackages = "com.luna.security.service")
@Log4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final DataSource dataSource;
	
	@Bean
	public UserDetailsService customUserService() {
		return new CustomUserDetailsService();
	}

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//		log.info("configure...................");
//		auth.inMemoryAuthentication().withUser("admin").password("{noop}admin").roles("ADMIN");
//		//$2a$10$dunYl.Mp6CLNks7m8wSnGehTywXUTeCKxRlzevUnsqseBAUFotIC2
//		
//		
//		auth.inMemoryAuthentication()
//		.withUser("member")
//		.password("$2a$10$dunYl.Mp6CLNks7m8wSnGehTywXUTeCKxRlzevUnsqseBAUFotIC2").roles("MEMBER");
//	}

//	@Override
//	 protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		 
//	 log.info("configure JDBC ............................");
//	
//	 String queryUser = "select userid , userpw , enabled from tbl_member where  userid = ? ";
//	 String queryDetails = "select userid, auth from tbl_member_auth where userid  = ? ";
//	
//	 auth.jdbcAuthentication()
//	 .dataSource(dataSource)
//	 .passwordEncoder(passwordEncoder())
//	 .usersByUsernameQuery(queryUser)
//	 .authoritiesByUsernameQuery(queryDetails);
//	 
//	 
//	 }

	@Override
	public void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/sample/all").permitAll().antMatchers("/diary/list")
				.access("hasRole('ROLE_ADMIN')").antMatchers("/sample/member").access("hasRole('ROLE_MEMBER')");

		http.formLogin();

		http.exceptionHandling().accessDeniedHandler(accessDeniedHandler());

		http.formLogin().loginPage("/customLogin").loginProcessingUrl("/login").successHandler(loginSuccessHandler());

		http.logout().logoutUrl("/customLogout").invalidateHttpSession(true).deleteCookies("remember-me",
				"JSESSION_ID");
		
	}
	

	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(customUserService()).passwordEncoder(passwordEncoder());
	}


	@Bean
	public AccessDeniedHandler accessDeniedHandler() {

		AccessDeniedHandler customAccessDeniedHandler = new CustomAccessDeniedHandler();

		return customAccessDeniedHandler;

	}

	@Bean
	public AuthenticationSuccessHandler loginSuccessHandler() {
		return new CustomLoginAccessHandler();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

	

}
