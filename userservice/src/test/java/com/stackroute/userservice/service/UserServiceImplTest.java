package com.stackroute.userservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.repository.UserRepository;

import java.util.Optional;

public class UserServiceImplTest {
	@Mock
	private UserRepository userRepository;

	private User user;
	@InjectMocks
	private UserServiceImpl userServiceImpl;

	Optional<User> optional;

	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		user = new User();
		user.setId(1);
		user.setEmailId("test email");
		user.setFirstName("test first name");
		user.setLastName("test last name");
		user.setPassword("123456");
		optional = Optional.of(user);
	}

	@Test
	public void testAddUserSuccess() throws UserAlreadyExistsException {

		Mockito.when(userRepository.save(user)).thenReturn(user);
		User fetchedUser =userServiceImpl.addUser(user);
		assertEquals(user, fetchedUser);

	}


	@Test
	public void testValidateUser() throws UserNotFoundException {
		Mockito.when(userRepository.findByEmailIdAndPassword("test email", "123456")).thenReturn(user);
		User fetchedUser = userServiceImpl.validateUser("test email", "123456");
		assertEquals("test email", fetchedUser.getEmailId());
	}

}
