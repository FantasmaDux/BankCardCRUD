package com.example.bankcards.service.cardRequest;

import com.example.bankcards.dto.CardDto;
import com.example.bankcards.dto.CardRequestDto;
import com.example.bankcards.entity.User;
import com.example.bankcards.request.CardCreationRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public class CardRequestServiceImpl implements CardRequestService {
    @Override
    public Page<CardRequestDto> getAllRequests(Pageable pageable) {
        return null;
    }

    @Override
    public Page<CardRequestDto> getUserRequests(User user, Pageable pageable) {
        return null;
    }

    @Override
    public CardDto approveRequest(UUID requestId) {
        CardCreationRequest cardCreationRequest = convertTo;
    }

    @Override
    public void rejectRequest(UUID requestId) {

    }

    @Override
    public CardRequestDto getRequestStatus(UUID requestId) {
        return null;
    }

    @Override
    public CardRequestDto createRequest(CardCreationRequest cardCreationRequest) {
        return null;
    }
}
