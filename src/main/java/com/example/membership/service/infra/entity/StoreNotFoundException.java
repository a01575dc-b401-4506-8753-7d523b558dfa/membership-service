package com.example.membership.service.infra.entity;

public class StoreNotFoundException extends RuntimeException {
    public StoreNotFoundException(long storeId) {
        super("store is not found [id=" + storeId + "]");
    }
}
