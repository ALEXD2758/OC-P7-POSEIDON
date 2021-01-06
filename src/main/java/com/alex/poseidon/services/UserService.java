package com.alex.poseidon.services;

import com.alex.poseidon.models.UserModel;
import com.alex.poseidon.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

	private UserRepository userRep;

	@Autowired
	public UserService(UserRepository userRep) {
		this.userRep = userRep;
	}

	/**
	 * Get a list of all users
	 *
	 * @return list of UserModel containing all user models
	 */
	public List<UserModel> getAllUsers() {
		return userRep.findAll();
	}

	/**
	 * Check if a username (e-mail address) already exists
	 * @param username the e-mail address of the user
	 * @return true if ID already exists
	 * @return false if ID doesn't exist
	 */
	public boolean checkIfUserExistsByUsername(String username) {
		return userRep.existsByUsername(username);
	}

	/**
	 * Check if an Id already exists
	 * @param id the user ID
	 * @return true if ID already exists
	 * @return false if ID doesn't exist
	 */
	public boolean checkIfUserExistsById(int id) {
		return userRep.existsById(id);
	}

	/**
	 * Get a user model by ID
	 * @param id the user ID
	 * @return UserModel found with the ID
	 */
	public UserModel getUserById(int id) {
		return userRep.findById(id);
	}

	/**
	 * Get a user model by email address
	 * @param username the e-mail address
	 * @return UserModel found with the address
	 */
	public UserModel getUserByEmail(String username) {
		return userRep.findByUsername(username);
	}

	/**
	 * Save a new user in the DB
	 * @param user the UserModel to save
	 */
	public void saveUser(UserModel user) {
		userRep.save(user);
	}

	/**
	 * Delete an existent user from the DB
	 * @param id the user ID
	 */
	public void deleteUserById(int id) {
		userRep.deleteById(id);
	}

	/**
	 * Set an encoded password of the user password (non-encrypted)
	 *
	 * @param user is the user logged-in
	 * @return Encrypted password in the UserModel
	 */
	public UserModel setUpUserModel(UserModel user) {
		user.setPassword(new Pbkdf2PasswordEncoder().encode(user.getPassword()));
		return user;
	}
}