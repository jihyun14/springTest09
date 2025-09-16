package com.example.demo.comment;

import java.time.LocalDateTime;

import com.example.demo.Post;
import com.example.demo.user.SiteUser;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Comment {
   @Id // 카피바라는 이 멤버변수를 통해서 각각의 데이터들을 구분할 것
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   // 위의 어노테이션은 카피바라가 일일이 번호 붙이기 귀찮아서 들어온 순서대로 1번부터 부여할 것임을 선언
   private Long id;
   
   @Column(columnDefinition = "TEXT")
   private String content;
   
 
   private LocalDateTime createAt;
   
   private LocalDateTime modifiedAt;
   
   @ManyToOne // 여러개의 댓글이 하나의 게시글을 바라봄
   private Post post;
   
   @ManyToOne
   private SiteUser author;

   
   public Long getId() {
	return id;
   }

   public String getContent() {
	return content;
   }

   public LocalDateTime getCreateAt() {
	return createAt;
   }

   public Post getPost() {
	return post;
   }

   public SiteUser getAuthor() {
	return author;
   }

   public void setId(Long id) {
	this.id = id;
   }

   public void setContent(String content) {
	this.content = content;
   }

   public void setCreateAt(LocalDateTime createAt) {
	this.createAt = createAt;
   }

   public void setPost(Post post) {
	this.post = post;
   }

   public void setAuthor(SiteUser author) {
	this.author = author;
   }

   public LocalDateTime getModifiedAt() {
	return modifiedAt;
   }

   public void setModifiedAt(LocalDateTime modifiedAt) {
	this.modifiedAt = modifiedAt;
   }
   
   
   
   
   
   
   
   
   
}
