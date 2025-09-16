package com.example.demo.comment;


import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.demo.Post;
import com.example.demo.user.SiteUser;

@Service
public class CommentServiceImpl implements CommentService {

	private final CommentRepository commentRepository;
	
	public CommentServiceImpl(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}
		
	
	@Override
	public void create(Post post, String content, SiteUser author) {
		// TODO Auto-generated method stub
		Comment comment = new Comment();
		comment.setAuthor(author);
		comment.setCreateAt(LocalDateTime.now());
		comment.setPost(post);
		comment.setContent(content);
		this.commentRepository.save(comment);
	}


	@Override
	public Comment getComment(Long id) {
		// TODO Auto-generated method stub
		return this.commentRepository.findById(id)
				.orElseThrow(()-> new IllegalArgumentException("해당 댓글은 없습니다."));
	
	}

	@Override
	public void modify(Comment cmt, String content) {
		// TODO Auto-generated method stub
		cmt.setContent(content);
		cmt.setModifiedAt(LocalDateTime.now());
		this.commentRepository.save(cmt);
	}

	@Override
	public void delete(Comment cmt) {
		this.commentRepository.delete(cmt);
		
	}
	
	

}
