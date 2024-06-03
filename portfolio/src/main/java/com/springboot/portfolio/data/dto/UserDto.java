package com.springboot.portfolio.data.dto;

import com.springboot.portfolio.data.entity.Project;
//import com.springboot.portfolio.data.entity.Userstack;
import com.springboot.portfolio.data.entity.Userstack;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String user_id;  // user table user_id
    private String full_name;
    private String password;
    private String github;
    private String blog;
    private String img;
}
