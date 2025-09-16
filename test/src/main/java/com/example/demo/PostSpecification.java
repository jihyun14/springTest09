package com.example.demo;

import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

// 해당 객체에 대한 정의서
// -> Post 객체를 검색하기 위한 세부사항 정의 클래스
public class PostSpecification {

	//Post 엔티티에 대한 검색 조건 명세를 위한 메소드
	public static Specification<Post> search(String keyword){
		return new Specification<Post>() {

			// 오버라이딩된 인터페이스와 객체들은 전부 JPA와 관련된 객체들.
			// JPA Criteria
			// 객체지향 프로그래밍을 기반으로 DB의 쿼리를 작성할 수 있도록 해주는 API
			// CriteriaBuilder : 쿼리의 조건을 생성하는데 사용되는 객체
			// CriteriaBuilder : 쿼리의 구조를 정의하는 객체
			@Override
			public Predicate toPredicate(Root<Post> root, CriteriaQuery<?> query, CriteriaBuilder cb) {			
				query.distinct(true); //중복제거
				//cb :CriteriaBuilder 줄여서 쓴거 
				return cb.or(
						cb.like(root.get("title"),"%" +keyword +"%"),
						cb.like(root.get("content"),"%" +keyword +"%")
						);
			}
			
		};
		
	}
	
	
	
}
