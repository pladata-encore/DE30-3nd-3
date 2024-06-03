package com.springboot.portfolio.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Userstack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userStackId;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name="skill", nullable = false)
    private Skills skill;
}
