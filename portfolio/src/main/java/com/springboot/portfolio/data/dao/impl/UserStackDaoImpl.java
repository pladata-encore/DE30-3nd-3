package com.springboot.portfolio.data.dao.impl;

import com.springboot.portfolio.data.dao.UserStackDao;
import com.springboot.portfolio.data.dto.UserStackDto;
import com.springboot.portfolio.data.entity.Userstack;
import com.springboot.portfolio.data.repository.UserStackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserStackDaoImpl implements UserStackDao {
    private final UserStackRepository userStackRepository;

    @Autowired
    public UserStackDaoImpl(UserStackRepository userStackRepository) {
        this.userStackRepository = userStackRepository;
    }

    @Override
    public Userstack selectUserStack(Long id) throws Exception {
        Optional<Userstack> userstack =  userStackRepository.findById(id);
        if(userstack.isPresent()) {
            return userstack.get();
        }
        throw new Exception();
    }

    @Override
    public List<Userstack> getUserStacks(String userId) {
        return userStackRepository.getUserStacks(userId);
    }

    @Override
    public Userstack createUserStack(Userstack userstack) {
        return userStackRepository.save(userstack);
    }

    @Override
    public void deleteUserStack(Long id) {
        userStackRepository.deleteById(id);
    }
}
