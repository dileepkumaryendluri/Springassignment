package com.springframework.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.springframework.domain.User;
import com.springframework.services.UserService;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("users", userService.listAllUsers());
        return "users";
    }

    @RequestMapping(value="user/{name}", method = RequestMethod.GET)
    public String showUser(@PathVariable String name, Model model){
        model.addAttribute("user", userService.getuserByName(name));
        return "usershow";
    }
    
    @RequestMapping(value="/{name}/birthday", method = RequestMethod.GET)
    @ResponseBody 
    public String getBirthday(@PathVariable String name){
      User user = userService.getuserByName(name);
        return user.getBirthdate();
    }
    
    
    @RequestMapping(value="/{name}/age", method = RequestMethod.GET)
    @ResponseBody 
    public Integer getAge(@PathVariable String name){
      User user = userService.getuserByName(name);
        return user.getAge();
    }
    
    @RequestMapping(value = "/user", method = RequestMethod.POST) 
    @ResponseBody
    public ResponseEntity<?> saveUser(@Valid @RequestBody User user) throws Exception{
    	
    	User user1 = userService.getuserByName(user.getUserName());
    	 if (user1!=null) {
    		 return new ResponseEntity<>("User already exist",HttpStatus.CONFLICT);
    	 } 
    	
        userService.saveUser(user);
    	 

        return new ResponseEntity<>(user,  HttpStatus.OK);
        	
    }
    
    
  	@RequestMapping(value = "/user/{name}", method = RequestMethod.PUT)
    @ResponseBody 
    public ResponseEntity<User> updateUser(@PathVariable String name, @Valid @RequestBody User user){
    	User user1 = userService.getuserByName(name);
    	user1.setAge(user.getAge());
    	user1.setBirthdate(user.getBirthdate());
    	
        userService.saveUser(user1);

        return new ResponseEntity<>(user1,  HttpStatus.OK);
    }

}
;