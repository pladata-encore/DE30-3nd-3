package com.springboot.portfolio.controller;

import com.springboot.portfolio.data.dto.ExperienceDto;
import com.springboot.portfolio.data.dto.ExperienceRequestDto;
import com.springboot.portfolio.service.ExperienceService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exp")
@AllArgsConstructor
public class ExperienceController {

    private final ExperienceService expService;

    @GetMapping("/get")
    public ResponseEntity<?> getExperience(long expId) throws Exception {
        ExperienceDto experienceDto = null;
        try {
            experienceDto = expService.selectExperience(expId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FOUND).body("예외가 발생했습니다.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(experienceDto);
    }

    @PostMapping("/save")
    public ResponseEntity<ExperienceDto> createExperience(
            @RequestBody ExperienceRequestDto experienceRequestDto
    ) throws Exception {
        if (experienceRequestDto.getContent() == null) {
            return ResponseEntity.badRequest().body(null);
        }
        ExperienceDto experienceDto1 = expService.createExperience(experienceRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(experienceDto1);
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateExperience(
            @RequestBody ExperienceDto experienceDto
    ) throws Exception{
        try {
            ExperienceDto experienceDto1
                    = expService.updateExperience(experienceDto);
            return ResponseEntity.status(HttpStatus.OK).body(experienceDto1);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FOUND).body("예외가 발생했습니다.");
        }

    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteExperience(long number) throws Exception {
        expService.deleteExperience(number);
        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제 되었습니다.");
    }

    @GetMapping("/user")
    public ResponseEntity<?> getByUserId() {
        List<ExperienceDto> experienceDtoList = null;
        try {
            experienceDtoList = expService.findByUserId();
            return ResponseEntity.status(HttpStatus.OK).body(experienceDtoList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FOUND).body("예외가 발생했습니다.");
        }
    }
}
