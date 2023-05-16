package com.example.membership.service.infra.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MembershipRepository  extends JpaRepository<Long,Membership> {
}
