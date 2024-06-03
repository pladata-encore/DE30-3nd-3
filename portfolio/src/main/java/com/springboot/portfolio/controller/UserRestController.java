package com.springboot.portfolio.controller;

import com.springboot.portfolio.data.dto.UserDto;
import com.springboot.portfolio.data.dto.UserLoginDto;
import com.springboot.portfolio.data.entity.User;
import com.springboot.portfolio.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Tag(name = "UserTest API", description = "Swagger 테스트용 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserRestController {
    private final UserService userService;
    // 유저 생성
    @PostMapping("/create")
    public ResponseEntity<User> createUser(
            @RequestBody UserLoginDto userLoginDto
    )
    {
        // userService 유저 생성
        User createdUser = userService.createUser(userLoginDto);
        return ResponseEntity.status(HttpStatus.OK).body(createdUser);
    }
    @GetMapping
            ("/findByid")
    public ResponseEntity<UserDto> findById(String user_id) throws Exception {
        User user = userService.findById(user_id);
        UserDto userResponse = convertToDto(user);
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }

    @PutMapping("/updateUserImage")
    public ResponseEntity<UserDto> updateUserImage(String user_id, String Img) {
        User updatedUser;
        try {
            updatedUser = userService.updateUserImage(user_id, Img);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        UserDto userResponse = convertToDto(updatedUser);
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }

    @PutMapping("/updateUserInfo")
    public ResponseEntity<UserDto> updateUserInfo(String user_id, String fullName, String github, String blog) {
        User updatedUser;
        try {
            updatedUser = userService.updateUserInfo(user_id, fullName, github, blog);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        UserDto userResponse = convertToDto(updatedUser);
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }

    @PutMapping("/updatePassword")
    public ResponseEntity<UserDto> updatePassword(String id, String password) {
        User updatedUser;
        try {
            updatedUser = userService.updatePassword(id, password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        UserDto userResponse = convertToDto(updatedUser);
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }

    private UserDto convertToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUser_id(user.getUser_id());
        userDto.setFull_name(user.getFull_name());
        userDto.setPassword(user.getPassword());
        userDto.setGithub(user.getGithub());
        userDto.setBlog(user.getBlog());
        userDto.setImg(user.getImage());
        return userDto;
    }
}
