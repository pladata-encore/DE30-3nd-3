package com.springboot.portfolio.data.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EducationDto {
    private Long eduId;
    private String school;
    private String graduate;
    private String major;
    private String start_date;
    private String end_date;
    private String content;
}
