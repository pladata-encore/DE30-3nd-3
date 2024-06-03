package com.springboot.portfolio.service.impl;

import com.springboot.portfolio.data.dao.LicenseDao;
import com.springboot.portfolio.data.dao.UserDao;
import com.springboot.portfolio.data.dto.LicenseDto;
import com.springboot.portfolio.data.dto.LicenseRequestDto;
import com.springboot.portfolio.data.entity.Experience;
import com.springboot.portfolio.data.entity.License;
import com.springboot.portfolio.data.entity.User;
import com.springboot.portfolio.data.repository.LicenseRepository;
import com.springboot.portfolio.service.LicenseService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LicenseServiceImpl implements LicenseService {
    private final LicenseDao licenseDao;
    private final UserDao userDao;
    @Autowired
    private HttpSession httpSession;
    @Autowired
    public LicenseServiceImpl(LicenseDao licenseDao, UserDao userDao) {
        this.licenseDao = licenseDao;
        this.userDao = userDao;
    }

    @Override
    public LicenseDto selectLicense(Long licenseId) throws Exception {
        License license = licenseDao.selectLicense(licenseId);
        LicenseDto licenseDto
                = new LicenseDto(
                        license.getLicenseId(),
                        license.getLicense_name());
        return licenseDto;
    }

    @Override
    public LicenseDto saveLicense(LicenseRequestDto licenseRequestDto) throws Exception {
        User newUser = (User) httpSession.getAttribute(("logined"));
        String userId = newUser.getUser_id();

        User user = userDao.findById(userId);
        License license = new License();
        license.setLicense_name(licenseRequestDto.getLicenseName());
        license.setUser(user);

        License insertedLicense = licenseDao.saveLicense(license);

        LicenseDto licenseDto
                = new LicenseDto(insertedLicense.getLicenseId(),
                insertedLicense.getLicense_name()
                );
        return licenseDto;
    }

    @Override
    public List<LicenseDto> findByUserId() {
        User newUser = (User) httpSession.getAttribute("logined");
        String userId = newUser.getUser_id();

        List<License> licenses = licenseDao.findByUserId(userId);

        return licenses.stream()
                .map(license -> new LicenseDto(
                        license.getLicenseId(),
                        license.getLicense_name()
                )).collect(Collectors.toList());
    }

    @Override
    public void deleteLicense(Long licenseId) throws Exception {
        License license = licenseDao.selectLicense(licenseId);
        if (license == null){
            //throw an excetpion or handle error
        }
        licenseDao.deleteById(licenseId);
    }
}
