package com.springboot.portfolio.service;

import com.springboot.portfolio.data.dto.SkillRequestDto;
import com.springboot.portfolio.data.dto.UserStackDto;
import com.springboot.portfolio.data.dto.UserStackResponseDto;

import java.util.List;

public interface UserStackService {
    UserStackDto selectUserStack(Long id) throws Exception;
    List<UserStackResponseDto> getUserStacks();
    UserStackDto createUserStack(SkillRequestDto skillRequestDto) throws Exception;
    void deleteUserStack(Long id) throws Exception;
}
