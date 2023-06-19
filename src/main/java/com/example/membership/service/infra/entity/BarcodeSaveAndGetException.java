package com.example.membership.service.infra.entity;

public class BarcodeSaveAndGetException extends RuntimeException {
    public BarcodeSaveAndGetException(Long userId) {
        super("problem generating barcode [id "+ userId + "]");
    }
}
