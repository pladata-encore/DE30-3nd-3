package com.springboot.portfolio.data.repository;

import com.springboot.portfolio.data.entity.License;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.beans.JavaBean;
import java.util.List;
import java.util.Optional;

public interface LicenseRepository extends JpaRepository<License, Long> {
    @Query("SELECT l FROM License l where l.user.user_id = :userId")
    List<License> findByUserId(@Param("userId") String userId);


}
