package com.springframework.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.springframework.configuration.RepositoryConfiguration;
import com.springframework.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {RepositoryConfiguration.class})
public class UserRepositoryTest {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Test
    public void testSaveProduct(){
        //setup product
        User user = new User();
        user.setAge(22);
        user.setUserName("Krishna");
        user.setBirthdate("");

        //save product, verify has ID value after save
        assertNull(user.getId()); //null before save
        userRepository.save(user);
        assertNotNull(user.getId()); //not null after save
        //fetch from DB
        User fetchedUser = userRepository.findById(user.getId()).orElse(null);

        //should not be null
        assertNotNull(fetchedUser);

        //should equal
        assertEquals(user.getId(), fetchedUser.getId());
        assertEquals(user.getUserName(), fetchedUser.getUserName());

        //update description and save
        fetchedUser.setUserName("Krishna");
        userRepository.save(fetchedUser);

        //get from DB, should be updated
        User fetchedUpdatedUser = userRepository.findById(fetchedUser.getId()).orElse(null);
        assertEquals(fetchedUser.getUserName(), fetchedUpdatedUser.getUserName());

        //verify count of products in DB
        long productCount = userRepository.count();
        assertEquals(productCount, 1);

        //get all products, list should only have one
        Iterable<User> users = userRepository.findAll();

        int count = 0;

        for(User u : users){
            count++;
        }

        assertEquals(count, 1);
    }
}
