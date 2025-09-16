package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")// 기본 주소 등록
public class UserController {

	
	private final UserRepository userRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {

		
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/signup")
	public String signup() {
		return "signup";
		
	}
	@PostMapping("/signup")
	public String singup(@RequestParam(value="username") String username, 
						 @RequestParam(value="password")  String password) {
		//@RequestParam : 폼으로 부터 넘어온 데이터를 잡기 어려워 한다면
		//@RequestParam 어노테이션을 통해 연결을 설정.
		SiteUser user = new SiteUser(username, passwordEncoder.encode(password), "USER");
		userRepository.save(user);
		return "redirect:/user/login" ;
	}
	
	
	
	
}
