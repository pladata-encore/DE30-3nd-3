package com.springboot.portfolio;

import com.springboot.portfolio.data.entity.User;
import com.springboot.portfolio.data.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTest {
    @Autowired
    UserRepository userRepository;

    @Test
    public void insertTest(){
        User user = new User();
        user.setUser_id("name@email.com");
        user.setFull_name("Su Hyeong lee 이수형");
        user.setPassword("password");
        user.setGithub("name@github.com");
        User savedUser = userRepository.save(user);
//        Assertions.assertNotNull();
    }
}
