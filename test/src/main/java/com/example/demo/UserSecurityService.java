package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.user.SiteUser;
import com.example.demo.user.UserRepository;

@Service
public class UserSecurityService implements UserDetailsService {

private final UserRepository userRepository;


public UserSecurityService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

 //throw -> SRP준수 위해 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		
		Optional<SiteUser>_user= this.userRepository.findByUsername(username);
		
	
		
		
		if(_user.isEmpty()) {
			
			throw new UsernameNotFoundException("사용자를 찾을 수 없음");
		}
		
		SiteUser user = _user.get();
	
		
		// 권한 부여를 위한 준비
		List<GrantedAuthority> auth = new ArrayList<>();
		// 시큐리티 기능으로 사용자의 역할에 따라 권한 부여도 가능해짐.
		//권한
		if("ADMIN".equals(user.getRole())) {
			auth.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
					
		}else {
			
			auth.add(new SimpleGrantedAuthority("ROLE_USER"));
			
			
			}
	
		
		// 기본적으로 시큐리티는 로그인 로그아웃을 처리할 때
		// 사용자의 정보가 User라고 하는 시큐리티가 제고앟는
		//클래스에 있다라고 생각함.
		// 우리는 회원정보를 SiteUser 엔티티에 담아놨기 때문에
		// 그 정보들을 시큐리티가 인식하는 User 클래스로 보내서 객체를 만들어줘야함.
		
		return new User(user.getUsername(),user.getPassword(), auth);
	
	
	}

	
	
}
