package com.example.demo.comment;

import com.example.demo.Post;
import com.example.demo.user.SiteUser;

public interface CommentService {
	Comment getComment(Long id);
	public void create(Post post, String content, SiteUser author);
	public void modify(Comment cmt, String content);
	public void delete(Comment cmt);
}
