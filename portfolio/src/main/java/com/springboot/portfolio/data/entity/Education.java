package com.springboot.portfolio.data.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Education {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long edu_id;

    @Column(nullable = false)
    private String school;

    @Column(nullable = false)
    private String graduate;

    @Column(nullable = false)
    private String major;

    @Column(nullable = false)
    private String start_date;

    @Column(nullable = false)
    private String end_date;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;
}
