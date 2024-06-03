package com.springboot.portfolio.data.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProjectFrontUpdateReqDto {
    private Long project_id;
    private String name;
    private String description;
    private String start_date;
    private String end_date;
    private String skill_list;
    private String role;
    private String github;
}
