package com.springboot.portfolio.data.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ExperienceRequestDto {
    private String companyName;
    private String startDate;
    private String endDate;
    private String content;
    private String job;
}
