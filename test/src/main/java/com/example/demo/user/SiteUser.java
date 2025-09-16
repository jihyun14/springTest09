package com.example.demo.user;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity

// User-> SiteUser 변경 이유 : User라는 이름으로는 이미 JPA에서 겹치는 컬럼들이 너무 많이 존재.
public class SiteUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long id;

	@Column(unique = true)
	private String username;
	
	private String password;
	
	private String role;

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getRole() {
		return role;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRole(String role) {
		this.role = role;
	}
	// 이부분 꼭 필요한가? => 현재 세팅 기준에서는 설정할 필요가 있음.
	// JPA가 요구함
	// (JPA는 모든 엔티티 클래스에 기본생성자를 요구)
	// 1. 리플렉션을 통한 객체 생성 구조이기 때문
	// - 리플렉션 : 프로그래밍에서 실행중인 프로그램의 구조를 분석하고 수정할 수 있는 기능을 제공하는 도구.
	// 2. 데이터베이스와의 맞추는 과정에서 생성자가 있어야지 그것이 가능.
	//	 - 매핑(엔티티와 데이터베이스의 매핑과정에 필요해서)
	public SiteUser() {
		
	}

	public SiteUser(String username, String password, String role) {

		this.username = username;
		this.password = password;
		this.role = role;
	}
	
	//id는 자동증가 하겠금 해뒀기 때문에 따로 생성자 필요없음.
	
	
	
    @Override
    public boolean equals(Object o) {
    	//객체 매번 비교하기 번거롭기 때문에 객체 비교를 위해 정의해두는 메소드.
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SiteUser user = (SiteUser) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() { // 겁색 속도 높이기 위해 사용.
        return Objects.hash(id);
    }
	
	
}
