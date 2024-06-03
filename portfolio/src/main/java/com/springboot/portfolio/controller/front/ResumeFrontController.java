package com.springboot.portfolio.controller.front;

import com.springboot.portfolio.data.dto.*;
import com.springboot.portfolio.service.*;
import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;
import java.util.List;

@Controller
public class ResumeFrontController {
    private final ExperienceService experienceService;

    private final EducationService educationService;

    private final LicenseService licenseService;
    private final UserStackService userStackService;
    private final SkillService skillService;

    public ResumeFrontController(ExperienceService experienceService, EducationService educationService, LicenseService licenseService, UserStackService userStackService, SkillService skillService) {
        this.experienceService = experienceService;
        this.educationService = educationService;
        this.licenseService = licenseService;
        this.userStackService = userStackService;
        this.skillService = skillService;
    }

    @GetMapping("/resume")
    public String getResume(Model model, HttpSession session) throws Exception {
        if (session.getAttribute("logined") != null) {
            // Experience
            List<ExperienceDto> experiences = experienceService.findByUserId();
            // 리스트를 역순으로 정렬
            Collections.reverse(experiences);
            // 모델에 추가
            model.addAttribute("experiences", experiences);

            //Education
            List<EducationDto> educations = educationService.findByUserId();
            Collections.reverse(educations);
            model.addAttribute("educations", educations);

            // License
            List<LicenseDto> licenses = licenseService.findByUserId();
            model.addAttribute("licenses", licenses);

            // userStack
            List<UserStackResponseDto> userStacks = userStackService.getUserStacks();
            model.addAttribute("userstacks", userStacks);

            // Skills
            List<SkillsDto> skills = skillService.getAllSkills();
            model.addAttribute("skills", skills);
//            skills.forEach(skill -> System.out.println("License: " + skills.()));

            // resume.html 템플릿을 렌더링
            return "resume";
        }else {
            return "needlogin";
        }
    }

}
