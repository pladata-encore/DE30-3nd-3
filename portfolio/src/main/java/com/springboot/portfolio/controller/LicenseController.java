package com.springboot.portfolio.controller;

import com.springboot.portfolio.data.dto.LicenseDto;
import com.springboot.portfolio.data.dto.LicenseRequestDto;
import com.springboot.portfolio.service.LicenseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/license")
@AllArgsConstructor
public class LicenseController {
    private final LicenseService licenseService;

    @GetMapping("/get")
    public ResponseEntity<?> getLicense(Long licenseId) throws Exception{
        LicenseDto licenseDto = null;
        try{
            licenseDto = licenseService.selectLicense(licenseId);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.FOUND).body("예외가 발생했습니다.");
        }
        return ResponseEntity.status((HttpStatus.OK)).body(licenseDto);
    }
    @PostMapping("/save")
    public ResponseEntity<LicenseDto> saveLicense(
            @RequestBody LicenseRequestDto licenseRequestDto
    ) throws Exception{
        if (licenseRequestDto.getLicenseName() == null){
            return ResponseEntity.badRequest().body(null);
        }
        LicenseDto licenseDto = licenseService.saveLicense(licenseRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(licenseDto);


    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteLicense(long licenseId) throws Exception{
        licenseService.deleteLicense(licenseId);
        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 종료되었습니다.");
    }
    @GetMapping("/user")
    public ResponseEntity<?> getByUserId(){
        List<LicenseDto> licenseDtoList = null;
        try{
            licenseDtoList = licenseService.findByUserId();
            return ResponseEntity.status(HttpStatus.OK).body(licenseDtoList);

        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.FOUND).body("예외가 발생했습니다.");
        }
    }
}
