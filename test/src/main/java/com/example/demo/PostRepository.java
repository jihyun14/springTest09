package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/*
 카피바라의 영역(DB/ Model단)
 JpaRepository 라는 다른 인터페이스를 상속받는 순간
 기본적인 데이터 처리가 가능해진다.
 save, findAll, findById, 
 //JpaSpecificationExecutor<Post> 페이징 처리 관련 -> 다중 상속 가능함.(애초에 interface임)
  */
@Repository
public interface PostRepository extends JpaRepository<Post,Long>, JpaSpecificationExecutor<Post>{

}
