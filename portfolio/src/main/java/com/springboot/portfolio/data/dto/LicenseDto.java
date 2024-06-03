package com.springboot.portfolio.data.dto;

import lombok.*;

import java.util.Date;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LicenseDto {
    private Long licenseId;
    private String licenseName;
}
