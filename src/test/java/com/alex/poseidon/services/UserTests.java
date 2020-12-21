package com.alex.poseidon.services;

import com.alex.poseidon.models.UserModel;
import com.alex.poseidon.repositories.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTests {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void saveUpdateFindDeleteUserShouldPerformTheirActionsAndSucceed() {
		UserModel user = new UserModel();
		user.setId(1);
		user.setUsername("admin@admin.com");
		user.setNonHashedPassword("Admininistrator12@%*");
		user.setPassword("4f454fg4f5g4f5g78dfg97df9g4dgd5");
        user.setFullname("Alexandre Dubois");
        user.setRole("ADMIN");

		// Save
		user = userRepository.save(user);
		Assert.assertNotNull(user.getId());
		Assert.assertTrue(user.getRole().equals("ADMIN"));

		// Update
		user.setFullname("Alex Dubois");
		user = userRepository.save(user);
		Assert.assertTrue(user.getFullname().equals("Alex Dubois"));

		// Find

		List<UserModel> listResult = userRepository.findAll();
		Assert.assertTrue(listResult.size() > 0);

		UserModel userResult = userRepository.findById(user.getId());
		Assert.assertTrue(userResult.getFullname().equals("Alex Dubois"));
		Assert.assertTrue(userResult.getRole().equals("ADMIN"));

		// Delete
		Integer id = user.getId();
		userRepository.delete(user);
		Optional<UserModel> userList = userRepository.findById(id);
		Assert.assertFalse(userList.isPresent());
	}
}