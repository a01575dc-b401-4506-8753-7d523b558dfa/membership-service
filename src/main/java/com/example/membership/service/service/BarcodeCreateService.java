package com.example.membership.service.service;

import com.example.membership.service.infra.entity.Membership;
import com.example.membership.service.infra.entity.MembershipRepository;
import com.example.membership.service.infra.entity.User;
import com.example.membership.service.infra.entity.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class BarcodeCreateService {

    private final UserRepository userRepository;
    private final MembershipRepository membershipRepository;

    public void createBarcode(Long userId) {
        final User user = userRepository.findById(userId).orElseThrow(() -> {
            throw new RuntimeException("id없음");
        });

        String uuid = RandomStringUtils.randomNumeric(10);
        Membership barcode = Membership.builder().id(uuid).user(user).build();

        membershipRepository.save(barcode);
    }
}


