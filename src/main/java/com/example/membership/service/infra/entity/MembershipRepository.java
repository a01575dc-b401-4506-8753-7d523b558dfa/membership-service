package com.example.membership.service.infra.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MembershipRepository  extends JpaRepository<Membership, String> {
    Optional<Membership> findByUserId(Long userId);
}
