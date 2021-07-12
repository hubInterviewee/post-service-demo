package com.hubtech;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hubtech.model.Comment;

public class InterviewDemoTest {

	/**
	 * 
	 * @param args Demonstrates adding comments make http call to
	 *             http://localhost:8080/getAllBlogs to see blog with no comments
	 *             run this method make another call to
	 *             http://localhost:8080/getAllBlogs to verify comment addition
	 * @throws JsonProcessingException
	 * 
	 */
	public static void main(String[] args) throws JsonProcessingException {
		Comment newComment = new Comment("test", "testComment", "Confessions of a Software Engineer", "akanoa");
		RestTemplate rt = new RestTemplate();
		ResponseEntity<String> response = rt.exchange("http://localhost:8080/addComment", HttpMethod.POST,
				new HttpEntity<Comment>(newComment), String.class);
		System.out.println(response.getBody());
	}
}
