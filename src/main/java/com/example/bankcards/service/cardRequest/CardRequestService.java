package com.example.bankcards.service.cardRequest;

import com.example.bankcards.dto.CardDto;
import com.example.bankcards.dto.CardRequestDto;
import com.example.bankcards.entity.User;
import com.example.bankcards.request.CardCreationRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CardRequestService {
    Page<CardRequestDto> getAllRequests(Pageable pageable);
    Page<CardRequestDto> getUserRequests(User user, Pageable pageable);
    CardRequestDto getRequestStatus(UUID requestId);
    CardRequestDto createRequest(CardCreationRequest cardCreationRequest);

    CardDto approveRequest(UUID requestId);
    void rejectRequest(UUID requestId);
}
