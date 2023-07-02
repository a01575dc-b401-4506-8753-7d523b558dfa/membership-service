package com.example.membership.service.infra.entity;

public class MembershipNotFoundException extends RuntimeException {
    public MembershipNotFoundException(String membershipId) {
        super("membership is not found [id=" + membershipId + "]");
    }
}
