package com.springboot.portfolio.data.dao;

import com.springboot.portfolio.data.entity.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectDao {
    Project selectProject(Long projectId) throws Exception;
    Project createProject(Project project);
    List<Project> findByUserId(String userId);
//    Optional<Project> findByprjId(Long projectId);
    Project updateProject(Project project) throws Exception;
    void deleteById(Long projectId) throws Exception;
}
