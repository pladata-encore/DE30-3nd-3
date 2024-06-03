package com.springboot.portfolio.data.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserStackDto {
    private Long userStackId;
    private String userId;
    private String skill;
}
