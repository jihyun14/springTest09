package com.example.demo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

//POST MAN api만들기

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/posts")
public class PostApiController {

	private final PostService postService;
	
	@GetMapping
	public ResponseEntity<List<Post>> getAllposts(){
		
		Page<Post> postPage = postService.findAll(0,"");
		List<Post>posts = postPage.getContent();
		return ResponseEntity.ok(posts);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Post> every(@PathVariable("id") Long id) {
		Post post = postService.상세보기구현하기(id);
		
		return ResponseEntity.ok(post);
		
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
