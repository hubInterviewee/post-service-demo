package com.hubtech.model;

import java.util.ArrayList;
import java.util.List;

public class BlogPost {
	//TODO: Add logic for title to be unique identifier
	private String title;
	private String body;
	private List<Comment> comments;
	
	public BlogPost(String title, String body) {
		this.title = title;
		this.body = body;
		this.comments = new ArrayList<>();
	}
	public String getTitle() {
		return title;
	}
	public String getBody() {
		return body;
	}
	public List<Comment> getComments() {
		return comments;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((body == null) ? 0 : body.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
	
	//would use cleaner equals method implementation
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BlogPost other = (BlogPost) obj;
		if (body == null) {
			if (other.body != null)
				return false;
		} else if (!body.equals(other.body))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	
	

}
