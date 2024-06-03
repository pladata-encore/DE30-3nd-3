package com.springboot.portfolio.data.repository;

import com.springboot.portfolio.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
//    Optional<User> findById(String user_id);
}
