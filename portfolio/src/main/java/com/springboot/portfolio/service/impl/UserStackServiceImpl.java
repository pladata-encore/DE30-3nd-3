package com.springboot.portfolio.service.impl;

import com.springboot.portfolio.data.dao.SkillDao;
import com.springboot.portfolio.data.dao.UserDao;
import com.springboot.portfolio.data.dao.UserStackDao;
import com.springboot.portfolio.data.dto.SkillRequestDto;
import com.springboot.portfolio.data.dto.UserStackDto;
import com.springboot.portfolio.data.dto.UserStackResponseDto;
import com.springboot.portfolio.data.entity.Skills;
import com.springboot.portfolio.data.entity.User;
import com.springboot.portfolio.data.entity.Userstack;
import com.springboot.portfolio.service.UserStackService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserStackServiceImpl implements UserStackService {
    private final UserStackDao userStackDao;
    private final SkillDao skillDao;
    private final UserDao userDao;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    public UserStackServiceImpl(UserStackDao userStackDao, SkillDao skillDao, UserDao userDao) {
        this.userStackDao = userStackDao;
        this.skillDao = skillDao;
        this.userDao = userDao;
    }

    @Override
    public UserStackDto selectUserStack(Long id) throws Exception {
        Userstack userstack = userStackDao.selectUserStack(id);
        UserStackDto userStackDto
                = new UserStackDto(userstack.getUserStackId(), userstack.getUser().getUser_id(),
                userstack.getSkill().getSkill_name());
        return userStackDto;
    }

    @Override
    public List<UserStackResponseDto> getUserStacks() {
        User newUser = (User) httpSession.getAttribute("logined");
        String userId = newUser.getUser_id();

        List<Userstack> userstackList = userStackDao.getUserStacks(userId);
        return userstackList.stream()
                .map(userstack -> {
                    try {
                        return new UserStackResponseDto(
                                userstack.getUserStackId(),
                                userstack.getUser().getUser_id(),
                                userstack.getSkill().getSkill_name(),
                                skillDao.selectSkill(userstack.getSkill().getSkill_name()).getImage()
                        );
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
    }


    @Override
    public UserStackDto createUserStack(SkillRequestDto skillRequestDto) throws Exception {
        // Log in된 User
        User newUser = (User) httpSession.getAttribute("logined");
        String userId = newUser.getUser_id();

        User user = userDao.findById(userId);
        Skills skills = skillDao.selectSkill(skillRequestDto.getSkill());

        Userstack userstack = new Userstack();
        userstack.setUser(user);
        userstack.setSkill(skills);

        Userstack createdUserStack = userStackDao.createUserStack(userstack);

        UserStackDto userStackDto
                 = new UserStackDto(createdUserStack.getUserStackId(),
                createdUserStack.getUser().getUser_id(),
                createdUserStack.getSkill().getSkill_name());
        return userStackDto;
    }

    @Override
    public void deleteUserStack(Long id) throws Exception {
        Userstack userstack= userStackDao.selectUserStack(id);
        if (userstack == null) {
            throw new Exception();
        }
        userStackDao.deleteUserStack(id);
    }
}
