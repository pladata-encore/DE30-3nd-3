package com.springboot.portfolio.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class User {
    @Id
    @Column(nullable = false)
    private String user_id;

    @Column(name="full_name")
    private String full_name;

    @Column(name="password", nullable=false)
    private String password;

    @Column(name="github")
    private String github;

    @Column(name="blog")
    private String blog;

    @Column(name="img")
    private String image;

    @OneToMany(mappedBy="user")
    private List<Project> projects;

    @OneToMany(mappedBy="user")

    private List<Userstack> userstacks;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Experience> experiences;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Experience> educations;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Experience> Licenses;
}
