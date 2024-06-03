package com.springboot.portfolio.data.dao.impl;

import com.springboot.portfolio.data.dao.LicenseDao;
import com.springboot.portfolio.data.entity.License;
import com.springboot.portfolio.data.repository.LicenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class LicenseDaoImpl implements LicenseDao {
    private final LicenseRepository licenseRepository;

    @Autowired
    public LicenseDaoImpl(LicenseRepository licenseRepository){
        this.licenseRepository = licenseRepository;
    }

    @Override
    public License selectLicense(Long licenseId) throws Exception {
        Optional<License> selectLicense = licenseRepository.findById(licenseId);
        if (selectLicense.isPresent()){
            return selectLicense.get();
        }
        throw new Exception();
    }

    @Override
    public License saveLicense(License license) {return licenseRepository.save(license);}

    @Override
    public List<License> findByUserId(String userId) {return licenseRepository.findByUserId(userId);}

    @Override
    public void deleteById(Long licenseId) throws Exception {
        License license = this.selectLicense(licenseId);
        licenseRepository.delete(license);
    }
}
