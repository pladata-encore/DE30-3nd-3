package com.springboot.portfolio.data.dto;

import lombok.*;

import java.util.Date;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto {
    private Long project_id;
    private String name;
    private String description;
    private String start_date;
    private String end_date;
    private String skill_list;
    private String role;
    private String img;
    private String github;
}