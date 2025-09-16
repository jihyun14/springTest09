package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

// 이 파일은 시큐리티의 설정을 담당할 파일.(스프링의 환경설정 파일(누렁이의 경비초소))
@Configuration // 이 파일이 스프링의 환경 설정 파일임을 의미하는 어노테이션
@EnableWebSecurity // 모든 요청된 URL이 스프링 시큐리티의 제어를 받도록 만드는 어노테이션.
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	// 두 개의 어노테이션 활성화를 통해 시큐리티를 스프링에 등록.
	// 시큐리티에는 SecurityFilterChain 클래스가 동작하면서
	// 모든 요청 URL에 이 클래스가 필터로 적용.
	// 스프링 시큐리티는 오늘 하는 것 말고도 더 세부적으로 분할하고 자세하게
	// 설정하는 것 또한 가능.
	
	 // http.authorizeHttpRequests(): http 요청에 대한 인증 및 권한부여 규칙 설명 메서드
	//authorizeHttpRequests : 요청 권한을 설정하는 람다식 내부에서 요청 매칭 조건과 권한 정의 
	//requestMatchers(new AntPathRequestMatcher("/**")) : 모든 URL 패턴에 대해 매칭.
	// AntPathRequestMatcher -> Ant스타일의 경로 매칭 제공.
	// -> 예를들어 / /home, /api **<- 모든 경로 포함의 의미
	
	// 시큐리티는 기본적으로 웹의 보안에 위배되는 공격들을 방어하는 것이 기본.
	// 예를들어 CSRF는 웹 보안 공격 중 하나인데 이러한 공격을 방지하기 위해
	// CSRF 토큰 세션을 통해 발행함.
	
	//그렇다면 h2console은 왜 문제가 발생하는가?
	// -h2console은 스프링을 기반으로 실행되는 건 맞음, 하지만 기본적으로는 DBMS이기 때문에
	// 스프링도 별도의 존재로 인식.
	// - 별 개의 존재다 보니 당연히 스프링과 상관이 없어서 CSRF 토큰을 발행할 수 없음.
	
	// h2-console을 위해 csrf도 허용해 준다 쳐도 화면 자체를 볼 수가 없는 증상이 발생.
	// x-Frame-Options : 클릭재킹 공격을 막기위해 사용.
	
	
	
		@Bean
	   SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		      http
		      .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests // 규칙 설정에 대한 코드
		            .requestMatchers(new AntPathRequestMatcher("/**")).permitAll()) // 규칙 설정에 대한 코드
		      .csrf((csrf) -> csrf
		    		  .ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**")))
		      .headers((headers) -> headers
		                .addHeaderWriter(new XFrameOptionsHeaderWriter(
		                    XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)))
		      .formLogin((formLogin) -> formLogin
		                .loginPage("/user/login") // 로그인 페이지의 URL
		                .defaultSuccessUrl("/show")) // 로그인 성공 시 이동할 기본 URL
		      .logout((logout) -> logout
		                .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout")) // 로그아웃 URL
		                .logoutSuccessUrl("/show") // 로그아웃 성공 시 이동할 URL
		                .invalidateHttpSession(true)) // 세션 무효화
		      ;
		      
		      return http.build();
		   }
	
	
		//db의 비밀번호 암호화.
		@Bean
		PasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
		
		}
	    @Bean
	    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
	        return authenticationConfiguration.getAuthenticationManager();
	    }
	
	
	
	
}
