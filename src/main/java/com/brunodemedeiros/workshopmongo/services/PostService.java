package com.brunodemedeiros.workshopmongo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brunodemedeiros.workshopmongo.domain.Post;
import com.brunodemedeiros.workshopmongo.repositories.PostRepository;
import com.brunodemedeiros.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	public Post findById(String id) {
		Post post = postRepository.findById(id).orElse(null);
		if (post == null) {
			throw new ObjectNotFoundException("Objeto não encontrado");
		}
		return post;
	}

}