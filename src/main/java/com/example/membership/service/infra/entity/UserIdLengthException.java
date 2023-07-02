package com.example.membership.service.infra.entity;

public class UserIdLengthException extends RuntimeException{
    public UserIdLengthException(Long userId){
        super("length of user ID is not correct [id " + userId + "]");
    }
}
