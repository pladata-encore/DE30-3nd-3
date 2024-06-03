package com.springboot.portfolio.service.impl;

import com.springboot.portfolio.data.dao.UserDao;
import com.springboot.portfolio.data.dto.UserDto;
import com.springboot.portfolio.data.dto.UserLoginDto;
import com.springboot.portfolio.data.entity.User;
import com.springboot.portfolio.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User createUser(UserLoginDto userLoginDto) {
        User user = new User();
        user.setUser_id(userLoginDto.getUser_id());
        user.setPassword(bCryptPasswordEncoder.encode(userLoginDto.getPassword()));
        return userDao.createUser(user);
    }

    @Override
    public User findById(String id) throws Exception {
        return userDao.findById(id);
    }

    @Override
    public User updateUserImage(String id, String imageUrl) throws Exception {
        return userDao.updateUserImage(id, imageUrl);
    }

    @Override
    public User updateUserInfo(String userId, String fullName, String github, String blog) throws Exception {
        return userDao.updateUserInfo(userId, fullName, github, blog);
    }

    @Override
    public User updatePassword(String userId, String password) throws Exception {
        return userDao.updatePassword(userId,bCryptPasswordEncoder.encode(password));
    }


}
