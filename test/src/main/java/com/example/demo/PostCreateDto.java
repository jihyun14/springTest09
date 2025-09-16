package com.example.demo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class PostCreateDto {

	//Post 엔티티에는 4개의 멤버변수가 있음
	//그 중 필요한 2개(사용자가 직접 입력)
	// 방문객이 급하냥에게 데이터를 보낼 때
	// 여기에 담아서 보내면 내용물이 섞이거나 분실될 위험이 없음.
	// 데이터를 나르는 객체..?
	
	@NotEmpty(message = "제목은 필수항목 입니다.")
	@Size(max=20, message = "제목은 200자를 넘을 수 없습니다.")
	private String title;
	
	@NotEmpty(message = "내용은 필수항목 입니다.")
	private String content;
	
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	//Dto 자체는 어차피 Post 클래스(엔티티)와는 다른 클래스.
	//그렇다고 상속으로 묶어두면 중복된 멤버변수들이 존재해서
	// 오히려 DTO는 힘을 쓰기 어려워짐.
	public Post toEntity() {
		return new Post(title, content);
	}
	
	
	
	
	
}
