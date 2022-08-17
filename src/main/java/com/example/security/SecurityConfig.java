package com.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		//security에서 제외할 url주소 (resources에 해당)
		web.ignoring().antMatchers("/css/**", "/js/**", "/image/**", "/fonts/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 권한에 따른 페이지 설정 
		// ex) 127.0.0.1:9090/ROOT/admin은 ADMIN권한만 접근가능
		http.authorizeRequests()
        	.antMatchers("/admin", "/admin/*").hasAuthority("ADMIN")
        	.antMatchers("/board","/board/*","/member/mypage").authenticated()
        	.anyRequest().permitAll()
        	.and()
        
        // 로그인 페이지 설정
        // 화면은 만들지만 실제 처리는 security에서 처리	
        .formLogin()
            .loginPage("/member/login")  // 127.0.0.1:9090/ROOT/member/login
            .loginProcessingUrl("/loginProcess") // <form action="여기에" />
            .usernameParameter("id")       // <input name="username"
            .passwordParameter("pw")       // <input name="password"
            .permitAll()						 // 모든 사용자 접근가능 	
            .defaultSuccessUrl("/")   // 로그인 성공시 이동할 페이지
            .successHandler(new MyLoginHandler()) // 권한별 페이지 전환 구현
            //.failureHandler(new MyLoginFailureHandler())
            .and()	
        
        // 로그아웃 페이지 설정. 직접적으로 만들지 않음
        .logout()	
        	.logoutUrl("/member/logout")   //로그아웃 수행할 페이지 url 단, post로 전송되어야 함
        	.logoutSuccessUrl("/")  //로그아웃 성공시 이동할 페이지
        	.invalidateHttpSession(true)   // 세션 삭제
        	.clearAuthentication(true)     // 인증정보 지우기
        	.permitAll()                   // 모든 사용자 접근가능 
			.and()
			
		// 접근할수 없는 페이지에 대한 처리 403	
		.exceptionHandling()
			.accessDeniedPage("/page403") //접근불가 페이지면 /ROOT/page403으로 이동
			.and();
		
		// <input type="hidden"사용하지 않아도 됨
		http.csrf().disable();
		http.headers().frameOptions().sameOrigin();
	}
	
}
