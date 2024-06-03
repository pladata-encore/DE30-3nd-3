package com.springboot.portfolio.controller;

import com.springboot.portfolio.data.dto.SkillRequestDto;
import com.springboot.portfolio.data.dto.UserStackDto;
import com.springboot.portfolio.data.dto.UserStackResponseDto;
import com.springboot.portfolio.service.SkillService;
import com.springboot.portfolio.service.UserStackService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userStack")
@AllArgsConstructor
public class UserStackController {
    private final UserStackService userStackService;
    private final SkillService skillService;

    @GetMapping("/getEach")
    public ResponseEntity<?> getEachUserStack(long stackId) throws Exception {
        UserStackDto userStackDto = null;
        try {
            userStackDto = userStackService.selectUserStack(stackId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FOUND).body("예외가 발생했습니다.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(userStackDto.toString());
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getUserStacks() {
        List<UserStackResponseDto> userStackDtoList = null;
        try {
            userStackDtoList = userStackService.getUserStacks();
            return ResponseEntity.status(HttpStatus.OK).body(userStackDtoList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FOUND).body("예외가 발생했습니다.");
        }
    }

    @PostMapping("/save")
    public ResponseEntity<UserStackResponseDto> createUserStack(
            @RequestBody SkillRequestDto skillRequestDto
            ) throws Exception {
        UserStackResponseDto userStackResponseDto = new UserStackResponseDto();
        UserStackDto userStackDto1 = userStackService.createUserStack(skillRequestDto);
        String skillName = skillRequestDto.getSkill();
        String skillImageUrl = skillService.selectSkill(skillName).getImage();

        userStackResponseDto.setUserStackId(userStackDto1.getUserStackId());
        userStackResponseDto.setUserId(userStackDto1.getUserId());
        userStackResponseDto.setSkill(userStackDto1.getSkill());
        userStackResponseDto.setImageUrl(skillImageUrl);

        return ResponseEntity.status(HttpStatus.OK).body(userStackResponseDto);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteExperience(long id) throws Exception {
        userStackService.deleteUserStack(id);
        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제 되었습니다.");
    }
}
