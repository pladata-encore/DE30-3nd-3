package com.springboot.portfolio.data.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProjectFrontRequestDto {
    private String name;
    private String description;
    private String start_date;
    private String end_date;
    private String skill_list;
    private String role;
    private String github;
}
