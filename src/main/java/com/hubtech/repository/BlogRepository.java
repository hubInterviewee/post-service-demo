package com.hubtech.repository;

import java.util.List;

import com.hubtech.model.BlogPost;

public interface BlogRepository {
	
	public void delete(String blogId);
	public BlogPost save (BlogPost blogPost);
	public BlogPost get(String id);
	public List<BlogPost> getAll();
	

}
