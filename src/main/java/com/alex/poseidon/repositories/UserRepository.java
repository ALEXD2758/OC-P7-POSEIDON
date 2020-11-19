package com.alex.poseidon.repositories;

import com.alex.poseidon.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;


public interface UserRepository extends JpaRepository<UserModel, Integer>, JpaSpecificationExecutor<UserModel> {

    UserModel findByUsername(String username);

    List<UserModel> findAll();
}
