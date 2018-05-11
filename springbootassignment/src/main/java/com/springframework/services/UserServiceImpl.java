package com.springframework.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springframework.domain.User;
import com.springframework.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Iterable<User> listAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }
    
    @Override
    public User getuserByName(String name) {
		return userRepository.findByUserName(name);
        
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
