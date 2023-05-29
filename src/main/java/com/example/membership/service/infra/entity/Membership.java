package com.example.membership.service.infra.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Entity
public class Membership {
    @Id
    private String id;
    @OneToOne(optional=false,fetch= FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private final User user;
}
