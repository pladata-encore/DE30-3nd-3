package com.springboot.portfolio.data.repository;

import com.springboot.portfolio.data.dto.UserStackDto;
import com.springboot.portfolio.data.entity.Userstack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserStackRepository extends JpaRepository<Userstack, Long> {
    @Query("SELECT us FROM Userstack us WHERE us.user.user_id = :userId")
    List<Userstack> getUserStacks(@Param("userId") String userId);

    @Query("DELETE FROM Userstack us WHERE us.skill.skill_name = :deleteSkill")
    void deleteUserStack(@Param("deleteSkill")String deleteSkill);

}
