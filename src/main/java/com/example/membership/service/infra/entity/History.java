package com.example.membership.service.infra.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Entity
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "point")
    private final Point point;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store")
    private final Store store;
    private final int amount;
    private final Long time;
}
