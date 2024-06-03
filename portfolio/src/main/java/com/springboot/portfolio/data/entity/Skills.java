package com.springboot.portfolio.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class Skills {
    @Id
    @Column(nullable = false)
    private String skill_name;

    @Column(nullable = false)
    private String image;

    @OneToMany(mappedBy = "skill")
    private List<Userstack> userstacks;
}
