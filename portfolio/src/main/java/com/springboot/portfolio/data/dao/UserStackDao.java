package com.springboot.portfolio.data.dao;

import com.springboot.portfolio.data.entity.Userstack;

import java.util.List;

public interface UserStackDao {
    Userstack selectUserStack(Long id) throws Exception;
    List<Userstack> getUserStacks(String userId);
    Userstack createUserStack(Userstack userstack);
    void deleteUserStack(Long id);
}
