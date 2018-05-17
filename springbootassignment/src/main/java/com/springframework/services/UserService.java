package com.springframework.services;


import com.springframework.domain.User;

public interface UserService {
    Iterable<User> listAllUsers();

    User getUserById(Integer id);
    User saveUser(User user);
    User getuserByName(String username);

	
    
}
