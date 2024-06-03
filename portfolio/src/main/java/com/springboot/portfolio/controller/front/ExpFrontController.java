package com.springboot.portfolio.controller.front;

import com.springboot.portfolio.data.dto.ExperienceDto;
import com.springboot.portfolio.data.dto.ExperienceRequestDto;
import com.springboot.portfolio.data.entity.Experience;
import com.springboot.portfolio.service.ExperienceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/experience")
public class ExpFrontController {
    private final ExperienceService experienceService;

    public ExpFrontController(ExperienceService experienceService) {
        this.experienceService = experienceService;
    }


    @PostMapping("/add")
    public String addExperience(@ModelAttribute ExperienceRequestDto experienceRequestDto, Model model) throws Exception {
        experienceService.createExperience(experienceRequestDto);
        return "redirect:/resume"; // Redirect to avoid form resubmission
    }

    @PostMapping("/update")
    public String updateExperience(@ModelAttribute ExperienceDto experienceDto, Model model) throws Exception {
        experienceService.updateExperience(experienceDto);
        return "redirect:/resume"; // Redirect to avoid form resubmission
    }

}
