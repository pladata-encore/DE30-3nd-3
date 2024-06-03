package com.springboot.portfolio.data.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ExperienceDto {
    private Long expId;
    private String companyName;
    private String startDate;
    private String endDate;
    private String content;
    private String job;
}
