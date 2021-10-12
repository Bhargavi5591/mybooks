package com.stackroute.userservice.controller;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@CrossOrigin
public class UserServiceController {

	@Autowired
	UserService userService;

	@PostMapping("/api/v1/addUser")
	public ResponseEntity<?> addUser(@RequestBody User user) {
		try {
			userService.addUser(user);
			return new ResponseEntity<User>(user, HttpStatus.CREATED);
		} catch (UserAlreadyExistsException e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}

	}

	@PostMapping("/api/v1/login")
	public ResponseEntity<?> validateUser(@RequestBody User user) {
		User result;
		try {
			result = userService.validateUser(user.getEmailId(), user.getPassword());
			if (null == result) {
				return new ResponseEntity<String>("Invalid User", HttpStatus.UNAUTHORIZED);
			}
			String token = generateToken(user);
			HashMap map = new HashMap<>();
			map.put("key", token);
			map.put("userId", result.getId());
			map.put("userName", result.getFirstName());
			return new ResponseEntity<HashMap>(map, HttpStatus.OK);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	private String generateToken(User user) {
		long duration = 10_000_00;
		return Jwts.builder().setSubject(user.getEmailId())
				.setExpiration(new Date(System.currentTimeMillis() + duration))
				.signWith(SignatureAlgorithm.HS256, "ibmkey").compact();

	}

}
