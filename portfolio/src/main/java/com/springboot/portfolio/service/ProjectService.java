package com.springboot.portfolio.service;

import com.springboot.portfolio.data.dto.ProjectDto;
import com.springboot.portfolio.data.dto.ProjectRequestDto;
import com.springboot.portfolio.data.entity.Project;

import java.util.List;

public interface ProjectService {
    ProjectDto selectProject(Long prjId) throws Exception;
    ProjectDto createProject(ProjectRequestDto projectRequestDto) throws Exception;

    List<ProjectDto> findByUserId();

    ProjectDto updateProject(ProjectDto projectDto) throws Exception;
    void deleteById(Long projectId) throws Exception;

}
