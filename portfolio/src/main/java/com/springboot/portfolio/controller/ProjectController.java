package com.springboot.portfolio.controller;

import com.springboot.portfolio.data.dto.ProjectDto;
import com.springboot.portfolio.data.dto.ProjectRequestDto;
import com.springboot.portfolio.data.dto.UserDto;
import com.springboot.portfolio.data.entity.Project;
import com.springboot.portfolio.data.entity.User;
import com.springboot.portfolio.service.ProjectService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "projectTest API", description = "Swagger 테스트용 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectService projectService;
    @GetMapping("/findById")
    public ResponseEntity<?> getProject(long projectId){
        ProjectDto projectDto = null;
        try{
            projectDto = projectService.selectProject(projectId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FOUND).body("예외가 발생했습니다.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(projectDto);
    }
    @PostMapping("/create")
    public ResponseEntity<ProjectDto> createProject(
            @RequestBody ProjectRequestDto projectRequestDto
    ) throws Exception {
        if (projectRequestDto.getDescription() == null){
            return ResponseEntity.badRequest().body(null);
        }
        ProjectDto createProjectDto = projectService.createProject(projectRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(createProjectDto);
    }

    @PutMapping("/updateProject")
    public ResponseEntity<?> updateProject(
            @RequestBody ProjectDto projectDto
    ) throws Exception {
        try{
            ProjectDto updatedProjectDto =
                    projectService.updateProject(projectDto);
            return ResponseEntity.status(HttpStatus.OK).body(updatedProjectDto);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus. FOUND).body("예외가 발생했습니다.");
        }

    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteProject(Long projectId) throws Exception {
        projectService.deleteById(projectId);
        return ResponseEntity.status(HttpStatus.OK).body(("정상적으로 삭제되었습니다."));
    }
    @GetMapping("/findByUserId")
    public ResponseEntity<?> getByUserId() {
        List<ProjectDto> projectDtoList = null;
        try{
            projectDtoList = projectService.findByUserId();
            return ResponseEntity.status(HttpStatus.OK).body(projectDtoList);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.FOUND).body("예외가 발생했습니다");
        }
    }
}
