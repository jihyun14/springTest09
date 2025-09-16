package com.example.demo;

import java.util.logging.ErrorManager;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// 전역의 예외를 관리하는 클래스
@ControllerAdvice // 모든 컨트롤러를 중앙집중식으로 관리하는 어노테이션.
public class GlobalExceptionHandler {

	@ExceptionHandler(IllegalArgumentException.class)
	public String handleIllegalArgumentException(IllegalArgumentException iae, Model model) {
		model.addAttribute("errorManager", iae.getMessage());
		
		return "error";
		
	}
	
}
