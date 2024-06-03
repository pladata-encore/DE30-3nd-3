package com.springboot.portfolio.data.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProjectRequestDto {
    private String name;
    private String description;
    private String start_date;
    private String end_date;
    private String skill_list;
    private String role;
    private String img;
    private String github;
}
