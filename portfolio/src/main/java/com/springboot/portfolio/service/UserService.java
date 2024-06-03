package com.springboot.portfolio.service;

import com.springboot.portfolio.data.dto.UserDto;
import com.springboot.portfolio.data.dto.UserLoginDto;
import com.springboot.portfolio.data.entity.User;

public interface UserService {
    User createUser(UserLoginDto userLoginDto);
    User findById(String id) throws Exception;
    User updateUserImage(String id, String imageUrl) throws Exception;
    User updateUserInfo(String userId, String fullName, String github, String blog) throws Exception;
    User updatePassword(String userId, String password) throws Exception;

}
