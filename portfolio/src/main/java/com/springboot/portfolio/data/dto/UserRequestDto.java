package com.springboot.portfolio.data.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
    private String user_id;  // user table user_id
    private String full_name;
    private String password;
    private String github;
    private String blog;
}
