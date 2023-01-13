package com.brunodemedeiros.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brunodemedeiros.workshopmongo.domain.User;
import com.brunodemedeiros.workshopmongo.dto.UserDto;
import com.brunodemedeiros.workshopmongo.repositories.UserRepository;
import com.brunodemedeiros.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public User findById(String id) {
		User user = userRepository.findById(id).orElse(null);
		if (user == null) {
			throw new ObjectNotFoundException("Objeto não encontrado");
		}
		return user;
	}
	
	public User insert(User user) {
		return userRepository.insert(user);
	}
	
	public void delete(String id) {
		findById(id);
	    userRepository.deleteById(id);
	}
	
	public User update(User user) {
		User newUser = userRepository.findById(user.getId()).orElse(null);
		updateData(newUser, user);
		return userRepository.save(newUser);
	}
	
	private void updateData(User newUser, User user) {
		newUser.setName(user.getName());
		newUser.setEmail(user.getEmail());		
	}

	public User fromDto(UserDto userDto) {
		return new User(userDto.getId(), userDto.getName(), userDto.getEmail());
	}
	
	
}
