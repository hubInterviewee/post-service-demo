package com.hubtech.repository;

import java.util.HashMap;
import java.util.Map;

import com.hubtech.model.Comment;

public class CommentRepositoryImpl implements CommentRepository {

	private Map<String, Comment> commentDatastore = new HashMap<>();

	@Override
	public void delete(String commentId) {
		commentDatastore.remove(commentId);

	}

	@Override
	public Comment save(Comment comment) {
		commentDatastore.put(comment.getId(), comment);
		return comment;
	}

	@Override
	public Comment get(String id) {
		return commentDatastore.get(id);
	}

}
