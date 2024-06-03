package com.springboot.portfolio.data.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserStackResponseDto {
    private Long userStackId;
    private String userId;
    private String skill;
    private String imageUrl;
}
