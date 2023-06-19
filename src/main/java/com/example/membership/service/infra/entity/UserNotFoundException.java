package com.example.membership.service.infra.entity;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long userId) {
        super("user is not found [id" + userId + "]");
    }
}
