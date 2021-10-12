package com.stackroute.userservice.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.stackroute.userservice.model.User;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserServiceRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	private User user;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		user = new User();
		user.setId(1);
		user.setEmailId("test email");
		user.setFirstName("test first name");
		user.setLastName("test last name");
		user.setPassword("123456");
	}

	@AfterEach
	public void tearDown() throws Exception {
		userRepository.deleteAll();
	}

	@Test
	public void testAddUserSuccess() {
		User fetchUser = userRepository.save(user);
		assertThat(user.getId(), is(fetchUser.getId()));
	}

	@Test
	public void testValidateUserSuccess() {
		userRepository.save(user);
		User fetchUser = userRepository.findByEmailIdAndPassword(user.getEmailId(), user.getPassword());
		assertThat(user.getId(), is(fetchUser.getId()));
	}

}
