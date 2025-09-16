package com.example.demo;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.comment.CommentRepository;
import com.example.demo.comment.Comment;
@SpringBootTest
class TestApplicationTests {

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Test
	void contextLoads() {
		// Post 클래스를 기반으로 객체를 만들고 활용해보기.
		//( Post 엔티티를 기반으로 데이터를 생성하고 리파지토리에 저장하기.)
		// 데이터 넣는 것
//		Post p1 = new Post();
//		p1.setContent("내용입니다.");
//		p1.setTitle("제목입니다.");
//		p1.setCreateAt(LocalDateTime.now());
//		this.postRepository.save(p1);
		
		//데이터 조회
		//Optional=> null값 불허 리스트
//		Optional<Post> o1 = this.postRepository.findById(2L);
//		
//		Post p = o1.get();
//		System.out.println(p.getTitle());
//		System.out.println(p.getContent());
		
		//service
		Optional<Comment> o1 = this.commentRepository.findById(1L);
		Comment p = o1.get();		
		System.out.println(p.getContent());
		
		
	}

}
