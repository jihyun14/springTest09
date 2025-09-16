package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

		// 이 클래스는 bean 객체다. 라는 이야기 이기도 함.
@Configuration // 이 클래스가 하나 이상의 Bean 메소드를 정의함을 나타내기도 함.
public class WebMvcConfig implements WebMvcConfigurer {
	//WebMvcConfigurer : 웹 서버의 mvc 구성을 위한 여러 메소드를 정의하는 인터페이스
	// -> 리소스 핸들러, 뷰 리졸버, 인터셉터 같은 다양한 웹 설정을 진행할 수 있음.
	
	private String uploadPath ="file:\\C:\\uploads\\";
	//private String uploadPath ="file://Users/username"; 맥, 리눅스용.
	
	//리소스를 처리하기 위한 리소스 핸들러
	// -> 웹 서비스(웹 어플리케이션)에서 정적 리소스를 처리하는 역할
	// 정적 리소스 : 이미지, css파일, js파일, 폰트 파일과 같이 서버에 저장되어
	// 			  클라이언트가 요청할 수 있는 파일들.
	
	//ResourceHandlerRegistry : 리소스 핸들러 등록시에 사용하는 클래스.
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	// 리소스들의 저장 결로를 설정.
    	// 
        registry.addResourceHandler("/images/**")
                .addResourceLocations(uploadPath);
    }
	
	
	
}
