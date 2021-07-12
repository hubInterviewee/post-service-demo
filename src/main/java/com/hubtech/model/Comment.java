package com.hubtech.model;

public class Comment {
	private String id;
	private String body;
	private String blogTitle;
	private String userId;
	
	public Comment(String id, String body, String blogTitle, String userId) {
		this.id = id;
		this.body = body;
		this.blogTitle = blogTitle;
		this.userId = userId;
	}
	
	public String getId() {
		return id;
	}
	public String getBody() {
		return body;
	}
	public String getBlogTitle() {
		return blogTitle;
	}
	public String getUserId() {
		return userId;
	}
	
	

}
