package com.example.membership.service.infra.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Membership {
    @Id
    private String id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user_id;
}
