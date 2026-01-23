package com.example.bankcards.service.cardRequest;

import com.example.bankcards.dto.CardDto;
import com.example.bankcards.dto.CardRequestDto;
import com.example.bankcards.entity.CardEntity;
import com.example.bankcards.entity.CardRequestEntity;
import com.example.bankcards.entity.UserEntity;
import com.example.bankcards.enums.RequestStatus;
import com.example.bankcards.exception.CardRequestNotFoundException;
import com.example.bankcards.repository.CardRequestRepository;
import com.example.bankcards.request.CardCreationRequest;
import com.example.bankcards.request.CreateCardRequest;
import com.example.bankcards.service.card.CardServiceImpl;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CardRequestServiceImpl implements CardRequestService {

    private final CardRequestRepository cardRequestRepository;
    private final UserRepository userRepository;
    private final CardServiceImpl cardServiceImpl;
    private ModelMapper modelMapper;

    @Override
    public Page<CardRequestDto> getAllRequests(Pageable pageable) {
        return cardRequestRepository.findAll(pageable).map(this::convertToDto);
    }

    @Override
    public Page<CardRequestDto> getUserRequests(UserEntity user, Pageable pageable) {
        return cardRequestRepository.findByUser(user, pageable)
                .map(this::convertToDto);
    }

    @Override
    @Transactional
    public CardDto approveRequest(UUID requestId, UserEntity admin) {
        CardRequestEntity request = cardRequestRepository.findById(requestId)
                .orElseThrow(() -> new CardRequestNotFoundException("Request not found"));

        request.setStatus(RequestStatus.APPROVED);
        request.setProcessedAt(LocalDateTime.now());
        request.setProcessedBy(admin);

        CardDto result = null;

        switch (request.getRequestType()) {
            case CREATION ->  {
                CardCreationRequest cardCreationRequest = new CardCreationRequest();
                cardCreationRequest.setUserId(request.getId());
                result = cardServiceImpl.createCard(cardCreationRequest);
            }
            case DELETION -> {
                cardServiceImpl.deleteCard(request.getId());
            }
        }
        cardRequestRepository.save(request);
        return result;
    }

    @Override
    public void rejectRequest(UUID requestId, UserEntity admin) {
        CardRequestEntity request = cardRequestRepository.findById(requestId)
                .orElseThrow(() -> new CardRequestNotFoundException("Request not found"));
        request.setStatus(RequestStatus.REJECTED);
        request.setProcessedAt(LocalDateTime.now());
        request.setProcessedBy(admin);

        cardRequestRepository.save(request);
    }

    @Override
    public CardRequestDto getRequestStatus(UUID requestId) {
        return cardRequestRepository.findById(requestId).map(this::convertToDto).orElseThrow(()
                -> new CardRequestNotFoundException("Request not found"));
    }

    @Override
    public CardRequestDto createRequest(CreateCardRequest cardRequest) {
        CardRequestEntity cardRequest = new CardRequestEntity();

        UserEntity user = userRepository.findById(createRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        cardRequest.setStatus(RequestStatus.CREATED);
        cardRequest.setUser(user);
        cardRequest.setRequestType(cardRequest.getRequestType());

        cardRequest = cardRequestRepository.save(cardRequest);
        return convertToDto(cardRequest);
    }

    private CardRequestDto convertToDto(CardRequestEntity entity) {
        return modelMapper.map(entity, CardRequestDto.class);
    }
}
