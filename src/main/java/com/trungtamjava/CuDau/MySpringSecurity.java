package com.trungtamjava.CuDau;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//cau hinh url // filter servlet
@Configuration
@EnableGlobalMethodSecurity(
		prePostEnabled = true,
		securedEnabled = true,
		jsr250Enabled = true)
		

public class MySpringSecurity extends WebSecurityConfigurerAdapter{
	@Autowired
	UserDetailsService userDetailsService;

	//xacthuc taikhoan
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	//cau hinh phan quyen
	protected void configure(HttpSecurity http) throws Exception {
		//phan quyen
		http.csrf().disable().authorizeRequests()
		.antMatchers("/admin/**").hasAnyRole("ADMIN")
		.antMatchers("/member/**").hasAnyRole("MEMBER")
		
		.anyRequest().permitAll()
		//cauhinh giao dien
		.and()
		.formLogin()
		.loginPage("/login")
		.loginProcessingUrl("/login")
		.usernameParameter("username")
		.defaultSuccessUrl("/home", true)
		.failureUrl("/login")
		.and()
		.logout()
		.logoutUrl("/logout")
		.logoutSuccessUrl("/login")
		//khi ng dung truy cap trai phep
		.and().exceptionHandling().accessDeniedPage("/access-deny");
		
		
	}
	public static void main(String[] args) {
		String rawPass = "123456";
		String encode = new BCryptPasswordEncoder().encode(rawPass);
		System.out.println(encode);
		//
		boolean check = new BCryptPasswordEncoder().matches(rawPass, encode);
		
		System.out.println(check);
	
	}
	
	

}
