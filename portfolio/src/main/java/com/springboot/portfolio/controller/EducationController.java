package com.springboot.portfolio.controller;

import com.springboot.portfolio.data.dto.EducationDto;
import com.springboot.portfolio.data.dto.EducationRequestDto;
import com.springboot.portfolio.data.entity.Education;
import com.springboot.portfolio.service.EducationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/edu")
@AllArgsConstructor
public class EducationController {
    private final EducationService eduService;

    @GetMapping("/get")
    public ResponseEntity<?> getEducation(long eduId) throws Exception {
        EducationDto educationDto = null;
        try {
            educationDto = eduService.selectEducation(eduId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FOUND).body(educationDto);
        }
        return ResponseEntity.status(HttpStatus.OK).body(educationDto);
    }
    @PostMapping("/save")
    public ResponseEntity<EducationDto> createEducation(
            @RequestBody EducationRequestDto educationRequestDto
    ) throws Exception {
//        if (educationRequestDto.getContent() == null) {
//            return ResponseEntity.badRequest().body(null);
//        }
        EducationDto educationDto1 = eduService.createEducataion(educationRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(educationDto1);
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateEducation(
            @RequestBody EducationDto educationDto
    ) throws Exception{
        try {
            EducationDto educationDto1
                    = eduService.updateEducation(educationDto);
            return ResponseEntity.status(HttpStatus.OK).body(educationDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FOUND).body("예외가 발생했습니다.");
        }
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteEducation(long number) throws Exception {
        eduService.deleteEducation(number);
        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
    }

    @GetMapping("/user")
    public ResponseEntity<?> getByUserId(){
        List<EducationDto> educationDtoList = null;
        try {
            educationDtoList = eduService.findByUserId();
            return ResponseEntity.status(HttpStatus.OK).body(educationDtoList);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.FOUND).body("예외가 발생했습니다.");
        }
    }
}
