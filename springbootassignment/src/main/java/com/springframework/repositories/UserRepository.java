package com.springframework.repositories;

import org.springframework.data.repository.CrudRepository;

import com.springframework.domain.User;

public interface UserRepository extends CrudRepository<User, Integer>{

	 public User findByUserName(String userName);
	 
	 
}
