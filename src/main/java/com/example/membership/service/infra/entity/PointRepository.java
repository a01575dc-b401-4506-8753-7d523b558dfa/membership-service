package com.example.membership.service.infra.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PointRepository extends JpaRepository<Point, Long> {
    Optional<Point> findByMembershipAndCategory(Membership membership, String category);
}
