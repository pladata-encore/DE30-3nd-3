package com.springboot.portfolio.service;

import com.springboot.portfolio.data.dto.LicenseDto;
import com.springboot.portfolio.data.dto.LicenseRequestDto;

import java.util.List;

public interface LicenseService {
    LicenseDto selectLicense(Long licenseId) throws Exception;
    LicenseDto saveLicense(LicenseRequestDto licenseRequestDto) throws Exception;
    List<LicenseDto> findByUserId();
    void deleteLicense(Long licenseId) throws Exception;

}
