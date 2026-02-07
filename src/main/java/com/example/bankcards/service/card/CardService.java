package com.example.bankcards.service.card;

import com.example.bankcards.dto.CardDto;
import com.example.bankcards.dto.CardFilterDto;
import com.example.bankcards.request.CardCreationRequest;
import com.example.bankcards.request.CardTransferRequest;
import com.example.bankcards.request.CardUpdateAdminRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.UUID;

public interface CardService {

    Page<CardDto> getAllCardsForAdmin(CardFilterDto filter, Pageable pageable);

    Page<CardDto> getAllCardsByUsernameForAdmin(String username, CardFilterDto filter, Pageable pageable);

    Page<CardDto> getAllCardsForUser(CardFilterDto filter, Pageable pageable);

    CardDto getCard(UUID cardId);

    CardDto createCard(CardCreationRequest cardCreationRequest);

    CardDto updateCard(UUID cardId, CardUpdateAdminRequest cardUpdateAdminRequest);

    void deleteCard(UUID cardId);

    BigDecimal getBalance(UUID cardId);

    void transfer(CardTransferRequest cardTransferRequest);

    void blockCard(UUID cardId);

    void activateCard(UUID cardId);
}
