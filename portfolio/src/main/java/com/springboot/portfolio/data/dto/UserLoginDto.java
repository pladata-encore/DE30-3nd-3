package com.springboot.portfolio.data.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDto {
    private String user_id;
    private String password;
}