package com.springboot.portfolio.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
/*

 */
@Entity
@Getter
@Setter
@ToString
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="project_id")
    private Long projectId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(name="start_date")
    private String startDate;

    @Column(name="end_date")
    private String endDate;

    @Column(name="skill_list")
    private String skillList;

    @Column(name="role")
    private String role;

    @Column(name="img")
    private String img;

    @Column(name="github")
    private String github;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;
}
