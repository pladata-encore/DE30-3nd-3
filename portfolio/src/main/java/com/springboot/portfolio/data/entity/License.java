package com.springboot.portfolio.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
public class License {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long licenseId;

    @Column(nullable = false)
    private String license_name;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;
}
