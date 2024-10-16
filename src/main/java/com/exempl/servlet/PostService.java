package com.exempl.servlet;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class PostService {
	 private final PostRepository repository;

	public PostService(PostRepository repository) {
		this.repository = repository;
	}

	 public Post save(Post post) {
	        return repository.save(post);
	    }

	    public List<Post> all() {
	        return repository.all();
	    }

	    public Optional<Post> getById(long id) {
	        return repository.getById(id);
	    }

	    public void removeById(long id) {
	        repository.removeById(id);
	    }

}
