package com.springboot.portfolio.data.dao;

import com.springboot.portfolio.data.entity.License;

import java.util.List;

public interface LicenseDao {
    License selectLicense(Long licenseId) throws Exception;
    License saveLicense(License license);
    List<License> findByUserId(String userId);
    void deleteById(Long licenseId) throws Exception;
}
