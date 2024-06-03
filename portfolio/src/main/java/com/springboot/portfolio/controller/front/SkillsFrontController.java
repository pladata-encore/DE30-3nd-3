package com.springboot.portfolio.controller.front;

import com.springboot.portfolio.data.dto.*;
import com.springboot.portfolio.service.UserStackService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/skills")
public class SkillsFrontController {
    private final UserStackService userStackService;

    public SkillsFrontController(UserStackService userStackService) {this.userStackService = userStackService;}

    @PostMapping("/add")
    public String addLicense(@ModelAttribute SkillRequestDto skillRequestDto, Model model) throws Exception {
        userStackService.createUserStack(skillRequestDto);
        return "redirect:/resume"; // Redirect to avoid form resubmission
    }
}
