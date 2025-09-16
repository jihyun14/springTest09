package com.example.demo.comment;

import org.springframework.data.jpa.repository.JpaRepository;

//저장공간생성
public interface CommentRepository extends JpaRepository<Comment,Long>{

}
