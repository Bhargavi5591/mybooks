package com.stackroute.userservice.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User addUser(User user) throws UserAlreadyExistsException {
		User existingUser = userRepository.findById(user.getId());
		if (existingUser == null) {
			userRepository.save(user);
			return user;
		}
		throw new UserAlreadyExistsException("User already exists");
	}

	public User validateUser(String emailId, String password) throws UserNotFoundException {
		User user = userRepository.findByEmailIdAndPassword(emailId, password);
		if (user == null) {
			throw new UserNotFoundException("User not found");
		}
		return user;
	}

}
