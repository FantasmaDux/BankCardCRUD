package com.example.bankcards.service.cardRequest;

import com.example.bankcards.dto.CardDto;
import com.example.bankcards.dto.CardRequestDto;
import com.example.bankcards.entity.UserEntity;
import com.example.bankcards.request.CreateCardRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CardRequestService {
    Page<CardRequestDto> getAllRequests(Pageable pageable);
    Page<CardRequestDto> getUserRequests(UserEntity user, Pageable pageable);
    CardRequestDto getRequestStatus(UUID requestId);
    CardRequestDto createRequest(CreateCardRequest cardCreationRequest);

    CardDto approveRequest(UUID requestId, UserEntity admin);
    void rejectRequest(UUID requestId, UserEntity admin);
}
