package com.springboot.portfolio.data.dao.impl;

import com.springboot.portfolio.data.dao.UserDao;
import com.springboot.portfolio.data.entity.User;
import com.springboot.portfolio.data.repository.UserRepository;
import jakarta.persistence.Column;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {
    private final UserRepository userRepository;
    @Override
    public User createUser(User user) {return userRepository.save(user);}

    @Override
    public User findById(String id) throws Exception {
        Optional<User> op = userRepository.findById(id);
        if(op.isPresent()){
            return op.get();
        }
        else{
            throw new Exception();
        }
    }

    @Override
    public User updateUserImage(String userId, String imageUrl) throws Exception {
        User findedUser = findById(userId);
        findedUser.setImage(imageUrl);
        return userRepository.save(findedUser);
    }

    @Override
    public User updateUserInfo(String userId, String FullName, String github, String blog) throws Exception {
        User findedUser = findById(userId);
        findedUser.setFull_name(FullName);
        findedUser.setGithub(github);
        findedUser.setBlog(blog);
        return userRepository.save(findedUser);
    }

    @Override
    public User updatePassword(String userId, String password) throws Exception {
        User findedUser = findById(userId);
        findedUser.setPassword(password);
        return userRepository.save(findedUser);
    }
}
