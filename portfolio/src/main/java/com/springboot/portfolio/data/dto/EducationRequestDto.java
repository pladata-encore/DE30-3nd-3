package com.springboot.portfolio.data.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EducationRequestDto {
    private String school;
    private String graduate;
    private String major;
    private String startDate;
    private String endDate;
    private String content;
}
