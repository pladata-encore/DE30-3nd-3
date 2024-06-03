package com.springboot.portfolio.controller.front;

import com.springboot.portfolio.data.dto.EducationDto;
import com.springboot.portfolio.data.dto.EducationRequestDto;
import com.springboot.portfolio.service.EducationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/education")
public class EduFrontController {
    private final EducationService educationService;

    public EduFrontController(EducationService educationService) {
        this.educationService = educationService;
    }

    @PostMapping("/add")
    public String addEducation(@ModelAttribute EducationRequestDto educationRequestDto, Model model) throws Exception {
        educationService.createEducataion(educationRequestDto);
        return "redirect:/resume";
    }
    @PostMapping("/update")
    public String updateEducatiom(@ModelAttribute EducationDto educationDto, Model model) throws Exception {
        educationService.updateEducation(educationDto);
        return "redirect:/resume";
    }
}
