package com.example.membership.service.service;

import com.example.membership.service.infra.entity.Membership;
import com.example.membership.service.infra.entity.MembershipRepository;
import com.example.membership.service.infra.entity.User;
import com.example.membership.service.infra.entity.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

/*
요청 값에는 사용자 ID가 포함됩니다.
사용자 ID는 9자리 숫자, 멤버십 바코드는 10자리 숫자 문자열입니다.
발급된 멤버십 바코드는 타인과 중복 발급이 불가합니다.
다음에 발급되는 멤버십 바코드는 예측할 수 없어야 합니다.
이미 발급된 아이디의 발급 요청 시 기존 멤버십 바코드를 반환합니다.
 */
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


