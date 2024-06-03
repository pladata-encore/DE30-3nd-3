package com.springboot.portfolio.controller;

import com.springboot.portfolio.data.dto.ExperienceDto;
import com.springboot.portfolio.data.dto.ExperienceRequestDto;
import com.springboot.portfolio.data.dto.SkillsDto;
import com.springboot.portfolio.service.SkillService;
import com.springboot.portfolio.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/skill")
public class SkillController {
    private final SkillService skillService;
    @PostMapping()
    public ResponseEntity<SkillsDto> createSkill(
            @RequestBody SkillsDto skillsDto
    ) throws Exception {
        SkillsDto skillsDto1 = skillService.createSkill(skillsDto);
        return ResponseEntity.status(HttpStatus.OK).body(skillsDto1);
    }

    @GetMapping()
    public ResponseEntity<?> selectSkill(
            String skill
    ) throws Exception {
        SkillsDto skillsDto = null;
        try {
            skillsDto = skillService.selectSkill(skill);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FOUND).body("예외가 발생했습니다.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(skillsDto);
    }
    @GetMapping("/getAllSkills")
    public ResponseEntity<?> getAllSkills(){
        List<SkillsDto> skillsDtoList = null;
        try{
            skillsDtoList = skillService.getAllSkills();
            return ResponseEntity.status(HttpStatus.OK).body(skillsDtoList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FOUND).body("예외가 발생했습니다.");
        }
    }
}
