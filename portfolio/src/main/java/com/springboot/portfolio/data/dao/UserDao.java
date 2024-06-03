package com.springboot.portfolio.data.dao;

import com.springboot.portfolio.data.entity.User;

public interface UserDao {
    User createUser(User user);
    User findById(String id) throws Exception;
//    User updateById(String id, UserDto userDto);
    User updateUserImage(String userId, String imageUrl) throws Exception;
    User updateUserInfo(String userId, String FullName, String github, String blog) throws Exception;
    User updatePassword(String userId, String Password) throws Exception;
}
