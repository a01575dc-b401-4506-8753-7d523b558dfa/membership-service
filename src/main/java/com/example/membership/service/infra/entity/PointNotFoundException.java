package com.example.membership.service.infra.entity;

public class PointNotFoundException extends RuntimeException {
    public PointNotFoundException(String membershipId, String category) {
        super("point is not found [membershipId=" + membershipId + "][category=" + category + "]");
    }
}
