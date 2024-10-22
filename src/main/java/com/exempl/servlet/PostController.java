package com.exempl.servlet;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;

@Controller
public class PostController {
	 private final PostService service;

	    public PostController(PostService service) {
	        this.service = service;
	    }

	    public List<Post> allPosts() {
	        return service.all();
	    }

	    public Optional<Post> getPostById(long id) {
	        return service.getById(id);
	    }

	    public Post savePost(Post post) {
	        return service.save(post);
	    }

	    public void deletePost(long id) {
	        service.removeById(id);
	    }
}
