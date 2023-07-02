package com.example.membership.service.service;

import com.example.membership.service.infra.entity.HistoryRepository;
import com.example.membership.service.infra.entity.MembershipRepository;
import com.example.membership.service.infra.entity.PointRepository;
import com.example.membership.service.infra.entity.StoreRepository;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PointAddServiceTest {
    @Autowired private StoreRepository storeRepository;
    @Autowired private MembershipRepository membershipRepository;
    @Autowired private HistoryRepository historyRepository;
    @Autowired private PointRepository pointRepository;

    private PointAddService pointAddService;

    @BeforeAll
    public void setUp() {
        pointAddService = new PointAddService(
                storeRepository,
                membershipRepository,
                historyRepository,
                pointRepository
        );
    }

    // TODO: add tests
}