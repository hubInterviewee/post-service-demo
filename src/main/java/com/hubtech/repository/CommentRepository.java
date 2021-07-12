package com.hubtech.repository;

import com.hubtech.model.BlogPost;
import com.hubtech.model.Comment;

public interface CommentRepository {
	public void delete(String blogId);
	public Comment save (Comment blogPost);
	public Comment get(String id);
}
