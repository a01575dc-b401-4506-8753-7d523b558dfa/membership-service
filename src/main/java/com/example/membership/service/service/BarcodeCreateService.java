package com.example.membership.service.service;

import com.example.membership.service.infra.entity.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BarcodeCreateService {

    private final UserRepository userRepository;
    private final MembershipRepository membershipRepository;

    public Membership createBarcode(Long userId) {
        validateUserId(userId);
        final User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        try {
            return membershipRepository.findByUserId(user.getId())
                    .orElseGet(() -> saveAndGetBarcode(user));
        }
        catch (DataIntegrityViolationException e){
            return membershipRepository.findByUserId(userId).orElseThrow(() -> new BarcodeSaveAndGetException(userId));
        }
    }

    private Membership saveAndGetBarcode(User user) {
        String uuid = RandomStringUtils.randomNumeric(10);
        return membershipRepository.save(
                Membership.builder()
                        .id(uuid)
                        .user(user)
                        .build()
        );
    }

    private void validateUserId(Long userId){
        if(userId == null) {
            throw new IllegalArgumentException("user Id is NULL");
        }
        if(userId <= 0) {
            throw new IllegalArgumentException("user ID is negative");
        }
        if(String.valueOf(userId).length() != 9) {
            throw new UserIdLengthException(userId);
        }
    }
}