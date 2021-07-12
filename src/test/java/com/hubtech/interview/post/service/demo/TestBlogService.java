package com.hubtech.interview.post.service.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import com.hubtech.model.BlogPost;
import com.hubtech.model.Comment;
import com.hubtech.service.BlogService;
//TODO: Add more tests for better coverage
public class TestBlogService {
	public static BlogPost TEST_BLOG = new BlogPost("Confessions of a Software Engineer", "Everyone wants me to build an app");
	
	private BlogService testBlogService;
	@BeforeEach
    public void init() {
		testBlogService = new BlogService();
    }
	
	@Test
	public void testCreateBlog() {
		assertEquals(null, testBlogService.getBlogById(TEST_BLOG.getTitle()).getBody());
		addDefaultBlog();
		assertEquals(TEST_BLOG, testBlogService.getBlogById(TEST_BLOG.getTitle()).getBody());
			
	}
	@Test
	public void testCreateNullOrDuplicateBlog() {
		assertEquals(HttpStatus.BAD_REQUEST, testBlogService.createBlog(new BlogPost(null, "")).getStatusCode());
		addDefaultBlog();
		assertEquals(HttpStatus.BAD_REQUEST, testBlogService.createBlog(TEST_BLOG).getStatusCode());
			
	}
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void testGetAllBlogs() {
		addDefaultBlog();
		List<BlogPost> blogs = (List<BlogPost>)(testBlogService.getAllBlogs().getBody());
		assertEquals(1, blogs.size());
		assertEquals(TEST_BLOG, blogs.get(0));
		
		
	}
	
	@Test
	public void testGetBlogById() {
		addDefaultBlog();
		assertEquals(TEST_BLOG, testBlogService.getBlogById(TEST_BLOG.getTitle()).getBody());
	}

	
	@Test
	public void testAddComment() {
		Comment testComment = new Comment("test", "testComment", TEST_BLOG.getTitle(), "akanoa");
		addDefaultBlog();
		BlogPost test = (BlogPost)(testBlogService.getBlogById(TEST_BLOG.getTitle()).getBody());
		assertEquals(0, test.getComments().size());
		testBlogService.addComment(testComment);
		test = (BlogPost)(testBlogService.getBlogById(TEST_BLOG.getTitle()).getBody());
		assertEquals(1, test.getComments().size());
		assertEquals(testComment, test.getComments().get(0));
	}

	private void addDefaultBlog() {
		testBlogService.createBlog(TEST_BLOG);
	}
	}

