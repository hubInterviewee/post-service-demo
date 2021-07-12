package com.hubtech.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hubtech.model.BlogPost;
//using hashmap as stand in for database- suspicious
public class BlogRepositoryImpl implements BlogRepository{
	
	private Map<String, BlogPost> blogDatastore= new HashMap<>();
	@Override
	public void delete(String blogId) {
		blogDatastore.remove(blogId);
		
	}

	@Override
	public BlogPost save(BlogPost blogPost) {
		blogDatastore.put(blogPost.getTitle(), blogPost);
		return blogPost;
	}

	@Override
	public BlogPost get(String id) {
		return blogDatastore.get(id);
	}

	@Override
	public List<BlogPost> getAll() {
		return new ArrayList<>(blogDatastore.values());
	}

}
