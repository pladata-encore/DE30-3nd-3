package com.springboot.portfolio.controller.front;

import com.springboot.portfolio.data.dto.ExperienceDto;
import com.springboot.portfolio.data.dto.ExperienceRequestDto;
import com.springboot.portfolio.data.dto.LicenseDto;
import com.springboot.portfolio.data.dto.LicenseRequestDto;
import com.springboot.portfolio.data.entity.Experience;
import com.springboot.portfolio.service.ExperienceService;
import com.springboot.portfolio.service.LicenseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/license")
public class LicenseFrontController {
    private final LicenseService licenseService;

    public LicenseFrontController(LicenseService licenseService) {this.licenseService = licenseService;}

    @PostMapping("/add")
    public String addLicense(@ModelAttribute LicenseRequestDto licenseRequestDto, Model model) throws Exception {
        licenseService.saveLicense(licenseRequestDto);
        return "redirect:/resume"; // Redirect to avoid form resubmission
    }
}
