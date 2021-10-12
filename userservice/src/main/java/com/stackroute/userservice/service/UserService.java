package com.stackroute.userservice.service;

import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.model.User;

public interface UserService {

	User addUser(User user) throws UserAlreadyExistsException;
	User validateUser(String emailId,String password) throws UserNotFoundException;
}
