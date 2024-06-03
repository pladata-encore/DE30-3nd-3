package com.springboot.portfolio.data.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
public class Experience {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long exp_id;

    @Column(nullable = false)
    private String company_name;

    @Column(nullable = false)
    private String start_date;

    @Column(nullable = false)
    private String end_date;

    @NotNull
    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String job;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;

}
