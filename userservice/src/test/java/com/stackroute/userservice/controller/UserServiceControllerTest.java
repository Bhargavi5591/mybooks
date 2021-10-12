package com.stackroute.userservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.service.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	@MockBean
	User user;

	@InjectMocks
	private UserServiceController userServiceController;

	@BeforeEach
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(userServiceController).build();

		user = new User();
		user.setId(1);
		user.setEmailId("test email");
		user.setFirstName("test first name");
		user.setLastName("test last name");
		user.setPassword("123456");

	}

	@Test
	public void testAddUser() throws Exception {

		Mockito.when(userService.addUser(user)).thenReturn(user);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/addUser").contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(user))).andExpect(MockMvcResultMatchers.status().isCreated())
				.andDo(MockMvcResultHandlers.print());

	}

	@Test
	public void testValidateUser() throws Exception {

		String email = "test email";
		String password = "123456";

		
		Mockito.when(userService.validateUser(email, password)).thenReturn(user);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/login").contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(user))).andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}

	// Parsing String format data into JSON format
	private static String jsonToString(final Object obj) throws JsonProcessingException {
		String result;
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			result = jsonContent;
		} catch (JsonProcessingException e) {
			result = "Json processing error";
		}
		return result;
	}
}

