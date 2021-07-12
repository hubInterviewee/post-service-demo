package com.hubtech.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hubtech.model.BlogPost;
import com.hubtech.model.Comment;
import com.hubtech.repository.BlogRepository;
import com.hubtech.repository.BlogRepositoryImpl;
import com.hubtech.repository.CommentRepository;
import com.hubtech.repository.CommentRepositoryImpl;

@RestController
public class BlogService {
	// TODO: Autowire repo objects to take advantage of spring dependency injection
	// TODO: Add integration testing framework like cucumber
	// TODO: Add api spec for documentation
	// TODO: Add method to update blogs and comments
	private BlogRepository blogRepo = new BlogRepositoryImpl();
	private CommentRepository commentRepo = new CommentRepositoryImpl();

	@GetMapping("/getBlogById/{id}")
	public @ResponseBody ResponseEntity<?> getBlogById(@PathVariable String id) {
		BlogPost retVal = blogRepo.get(id);

		return retVal != null ? new ResponseEntity<BlogPost>(retVal, HttpStatus.OK)
				: new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/getCommentById/{id}")
	public @ResponseBody ResponseEntity<?> getCommentById(@PathVariable String id) {
		Comment retVal = commentRepo.get(id);

		return retVal != null ? new ResponseEntity<Comment>(retVal, HttpStatus.OK)
				: new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
	}

	@PostMapping("/createBlog")
	public @ResponseBody ResponseEntity<?> createBlog(@RequestBody BlogPost blog) {
		ResponseEntity<?> retVal = null;
		if (blog == null || blog.getTitle() == null || blogRepo.get(blog.getTitle()) != null) {
			retVal = new ResponseEntity<String>("cannot create blog with null or duplicate id", HttpStatus.BAD_REQUEST);
		}
		blogRepo.save(blog);
		return retVal == null ? new ResponseEntity<BlogPost>(blog, HttpStatus.ACCEPTED) : retVal;
	}

	@GetMapping("/getAllBlogs")
	public @ResponseBody ResponseEntity<?> getAllBlogs() {
		// for demonstration
		addDefaultBlog();
		List<BlogPost> retVal = blogRepo.getAll();

		return retVal.size() != 0 ? new ResponseEntity<List<BlogPost>>(retVal, HttpStatus.OK)
				: new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping("/addComment")
	public @ResponseBody ResponseEntity<?> addComment(@RequestBody Comment comment) {
		String blogId = comment.getBlogTitle();
		ResponseEntity<?> retVal = null;
		BlogPost blog = blogRepo.get(blogId);
		if (blogId == null || blog == null) {
			retVal = new ResponseEntity<String>("cannot find blog id", HttpStatus.NOT_FOUND);
		} else {
			blog.getComments().add(comment);
			blogRepo.save(blog);
		}
		return retVal == null ? new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED) : retVal;
	}

	// this is a hack for demonstration purposes - adds a blog if there is none
	private void addDefaultBlog() {
		BlogPost testBlog = new BlogPost("Confessions of a Software Engineer", "Everyone wants me to build an app");
		if (blogRepo.getAll().size() == 0) {
			createBlog(testBlog);
		}

	}
}
