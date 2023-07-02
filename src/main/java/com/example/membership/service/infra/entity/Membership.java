package com.example.membership.service.infra.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
@Entity
public class Membership {
    @Id
    private final String id;
    @OneToOne(optional=false,fetch= FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private final User user;
}
