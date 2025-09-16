package com.example.demo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.user.SiteUser;

import jakarta.transaction.Transactional;


@Service
public class PostService {
//와플곰이 급하냥한테 부탁받은 일을 처리할 곳.
	
	//와플곰은 급하냥이 처리해 달라는 것만
	//처리해주면 된다.
	// -> 컨트롤러에서 서비스단으로 데이터 조회, 수정 삭제등을 요청.
	//	  그러면 그거만 해주면 됨.
	// -> 다만 데이터의 가공이나 검사 등은 여기서 수행.
	// -> 가공 검사등이 끝나면 와플곰은 카피바라에게 데이터 창고의 내용을 요청하거나
	// 	  혹은 데이터 창고에 내용들을 저장시킬 수 있음.
	// -> 카피바라는 본인만의 특별한 아이템(JPA)을 사용하여 데이터를 관리한다.
	
	// 파일의 저장경로는 우리가 이미 설정을 하긴 했음.
	// 다만 처음에 처리할 때는 해당 결로를 제대로 읽을 수 없거나 문제가 발생할 수 있음.
	// 이번 실습에서는 만약을 대비해서 경로를 직접지정하겠음.
	private String fileDir = "C:\\uploads\\";
	
	// 와플곰이 자신의 업무에 필요한 데이터 저장을 위해
	// 카피바라에게 전달하는 과정.
	
	private final PostRepository postRepository;
	private Long id;
	@Autowired
	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}
	
	//와플곰이 해야할 일
	// 데이터 관련 일이 필요하다.
	// CRUD 관리.(Create, Read, Update, Delete)
	
	//게시글 저장. 
	public Long save(PostCreateDto dto, MultipartFile file) throws IllegalStateException, IOException {
		// 저장할 때 리턴타입 없어도 저장가능.
		// 지금 같은 경우는 리턴타입을 추가해서 무언가 값을 받고 있음
		// -> 값을 통해서 데이터 저장 후의 로직을 처리하기 위함.
		
		String originalFileName = null;
		String storedFileName = null;

		// 비어있는지(파일 업로드를 했는지?)
		if(file!= null && !file.isEmpty()) {
			// 원래 사용자가 저장한 파일명
			originalFileName = file.getOriginalFilename();
			//고유한 파일명 생성
			storedFileName = UUID.randomUUID().toString()+"_"+originalFileName;
			
			//transferTo : 업로드된 파일의 내용을 지정도니 위치로 전송하는 기능을 수행
			// 스프링에서 흔히 파일 관련된 객체를 생성할 때 File 클래스를 기반으로
			// 만들어서 처리하는 경우가 대부분.
			file.transferTo(new File(fileDir + storedFileName));
		}
		
		Post post = new Post(dto.getTitle(),dto.getContent(),originalFileName,storedFileName);
		
		return postRepository.save(post).getId();
		
		//return postRepository.save(dto.toEntity()).getId();
		}
	
	
	// 모든 게시글 조회. 
//	public List<Post> findAll() {
//		return postRepository.findAll();
//	}
	//페이징처리 시작
	public Page<Post> findAll(int page, String keyword) {
		List<Sort.Order> sorts = new ArrayList<>();
		//정렬도 기준을 잡는게 가능
		sorts.add(Sort.Order.desc("createAt")); // 최신순으로 정렬.
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts)); // 페이지장 10개씩 보여주세요
		// PageRequest 
		// => 변수 page : 컨트롤러로 부터 넘어오는 파라미터,
		// 10 : 보여줄 개수.
		// Sort.by(sorts)정렬의 기준을 설정.
		
		//Specification: JPA에서 복잡한 쿼리를 처리하기 위해 만들어진 인터페이스
		// -> 기반은 Criteria
		// -> 목적은 동적인 쿼리를 생성할 때 주로활용하는 객체.
		//PostSpecification : Post 객체에 대한 검색조건을 정의
		Specification<Post> spec = PostSpecification.search(keyword);
				
		return postRepository.findAll(spec, pageable);
	}

	//IllegalArgumentException 추가.
	
	public Post 상세보기구현하기(Long id) {
		// TODO Auto-generated method stub
//		Optional<Post> 상세조회결과저장객체 = this.postRepository.findById(id);
//		return 상세조회결과저장객체.get();
		// 예외처리 적용하기 방법 1 : 람다를 활용한다(orElseThrow 메서드를 활용)
//		return this.postRepository.findById(id)
//				   .orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다."));
		
		// 람다 없이 하려면?
		Optional<Post> 상세조회결과저장객체 = this.postRepository.findById(id);
		if(!상세조회결과저장객체.isPresent()) {
			throw new IllegalArgumentException("해당 게시글이 없습니다.");
			
		}
		return 상세조회결과저장객체.get();
	}

	//-> 컴퓨터는 지금 어떤 게시글을 업데이트 해야하는지 정보 X
	//-> 어떤 걸 바꿔야할지 알려줘야한다.
	@Transactional // 객체 자체만 불러와서 잘 바꿔주고 멤버변수를 해당
				   // 엔티티를 통해 변경만 잘해주면 이전보다 쉽게 데이터를 수정할 수 있음
	public void update(Long id, PostCreateDto dto) {
		
		
		Optional<Post>post = postRepository.findById(id);
		
		Post p1 = post.get();
		post.get().update(dto.getTitle(),dto.getContent());
	}
	
	public void delete(Long id) {
		Optional<Post>post = postRepository.findById(id);
		Post p1 = post.get();
		postRepository.delete(p1);
	}
	//게시글 추천
	@Transactional
	public void recommend(Post post, SiteUser user) {
		// TODO Auto-generated method stub
		if(post.recommendation.contains(user)) { //만약에 이미 추천을 했던 유저라면
			post.recommendation.remove(user);
		}else {
			post.getRecommendation().add(user);
		}
		this.postRepository.save(post);
	}
	
	
}
