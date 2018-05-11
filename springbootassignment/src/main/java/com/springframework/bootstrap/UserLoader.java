package com.springframework.bootstrap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.springframework.domain.User;
import com.springframework.repositories.UserRepository;

import java.math.BigDecimal;

@Component
public class UserLoader implements ApplicationListener<ContextRefreshedEvent> {

    private UserRepository userRepository;

    private Logger log = LogManager.getLogger(UserLoader.class);

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

       User u1 = new User();
       u1.setUserName("John");
       u1.setBirthdate("1/1/1988");
       u1.setAge(25);
       userRepository.save(u1);

        log.info("User - id: " + u1.getId());

        User u2 = new User();
        u2.setUserName("Sam");
        u2.setBirthdate("2/1/1988");
        u2.setAge(25);
        userRepository.save(u2);

         log.info("User - id: " + u2.getId());
         
         User u3 = new User();
         u3.setUserName("Danny");
         u3.setBirthdate("3/1/1988");
         u3.setAge(29);
         userRepository.save(u3);

          log.info("User - id: " + u3.getId());
    }
}
