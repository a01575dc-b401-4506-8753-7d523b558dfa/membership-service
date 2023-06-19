package com.example.membership.service.service;

import com.example.membership.service.infra.entity.*;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@Transactional
class BarcodeCreateServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private MembershipRepository membershipRepository;
    @InjectMocks
    private BarcodeCreateService barcodeCreateService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private final Long userId = 123456789L;

    @Test
    void 바코드_발급_성공() {
        // userRepository.findById() 메소드가 Optional.empty()를 반환하도록 설정
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // barcodeCreate() 메소드를 호출하면 RuntimeException이 발생하는지 검증
        assertThrows(RuntimeException.class, () -> barcodeCreateService.createBarcode(userId));

        // userRepository.findById() 메소드가 1번 호출되었는지 검증
        verify(userRepository, times(1)).findById(userId);

        // membershipRepository.save() 메소드가 호출되지 않았는지 검증
        verify(membershipRepository, never()).save(any(Membership.class));


    }

    @Test
    void membership_Id_10자리_숫자_확인() {
        // 가상의 유저 객체 생성
        User user = new User(userId);

        // userRepository.findById() 메소드가 가상의 유저 객체를 반환하도록 설정
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // barcodeCreate() 메소드 호출
        barcodeCreateService.createBarcode(userId);

        // membershipRepository.save() 메소드에 전달된 Membership 객체 캡처
        ArgumentCaptor<Membership> captor = ArgumentCaptor.forClass(Membership.class);
        verify(membershipRepository).save(captor.capture());

        // 저장된 Membership 객체 가져오기
        Membership savedMembership = captor.getValue();

        assertTrue(savedMembership.getId().matches("\\d{10}"));
    }

    @Test
    void user_id_없는_경우() {
        // userRepository.findById() 메소드가 가상의 유저 객체를 반환하도록 설정
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // UserNotFoundException 이 동작하는지 확인
        assertThrows(UserNotFoundException.class, () -> barcodeCreateService.createBarcode(userId));
    }

    @Test
    void user_id_9자리_아닐때() {
        Long userId2 = 01234567L;
        User user = new User(userId2);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        assertThrows(UserIdLengthException.class, () -> barcodeCreateService.createBarcode(userId));
    }
}