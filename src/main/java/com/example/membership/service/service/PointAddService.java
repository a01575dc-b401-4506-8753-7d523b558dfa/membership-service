package com.example.membership.service.service;

import com.example.membership.service.infra.entity.*;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class PointAddService {
    private final StoreRepository storeRepository;
    private final MembershipRepository membershipRepository;
    private final HistoryRepository historyRepository;
    private final PointRepository pointRepository;

    @Transactional
    public void add(long storeId, String membershipId, int amount) {
        if (storeId <= 0) {
            throw new IllegalArgumentException("store id must be positive [id=" + storeId + "]");
        }
        if (StringUtils.isBlank(membershipId)) {
            throw new IllegalArgumentException("membership id must not be blank [id=" + membershipId + "]");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be positive [amount=" + amount + "]");
        }
        addInternal(storeId, membershipId, amount);
    }

    private void addInternal(long storeId, String membershipId, int amount) {
        final Store store = storeRepository.findById(storeId).orElseThrow(
                () -> new StoreNotFoundException(storeId));
        final Membership membership = membershipRepository.findById(membershipId).orElseThrow(
                () -> new MembershipNotFoundException(membershipId));
        final Point point = pointRepository.findByMembershipAndCategory(membership, store.getCategory()).orElseThrow(
                () -> new PointNotFoundException(membership.getId(), store.getCategory()));

        historyRepository.save(
                History.builder()
                       .point(point)
                       .store(store)
                       .amount(amount)
                       .time(System.currentTimeMillis())
                       .build()
        );

        // optimistic locking is enforced
        pointRepository.save(point.toBuilder().amount(point.getAmount() + amount).build());
    }
}
