package com.example.bankcards.service.card;

import com.example.bankcards.dto.CardDto;
import com.example.bankcards.dto.CardFilterDto;
import com.example.bankcards.entity.Card;
import com.example.bankcards.entity.User;
import com.example.bankcards.enums.CardStatus;
import com.example.bankcards.exception.CardNotFoundException;
import com.example.bankcards.repository.CardRepository;
import com.example.bankcards.request.CardCreationRequest;
import com.example.bankcards.request.CardTransferRequest;
import com.example.bankcards.request.CardUpdateAdminRequest;
import com.example.bankcards.util.CardUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final ModelMapper modelMapper;

    @Override
    public Page<CardDto> getAllCardsForAdmin(CardFilterDto filter, Pageable pageable) {
        return null;
    }

    @Override
    public Page<CardDto> getAllCardsByUsernameForAdmin(String username, CardFilterDto filter, Pageable pageable) {
        return null;
    }

    @Override
    public CardDto createCard(CardCreationRequest cardCreationRequest) {
        Card card = new Card();
        User user = userRepository.getUserById(cardCreationRequest.getUserId());

        card.setStatus(CardStatus.ACTIVE);
        card.setExpiryDate(CardUtils.generateCardExpireDate());
        card.setNumber(CardUtils.generateCardNumber());
        card.setCvv(CardUtils.generateCardCvv());
        card.setBalance(BigDecimal.ZERO);
        card.setOwner(user);

        card = cardRepository.save(card);
        return modelMapper.map(card, CardDto.class);
    }

    @Override
    public CardDto updateCard(UUID cardId, CardUpdateAdminRequest cardUpdateAdminRequest) {
        Card card = cardRepository.findById(cardId).orElseThrow(CardNotFoundException::new);

        card.setNumber(cardUpdateAdminRequest.getNumber());
        card.setStatus(cardUpdateAdminRequest.getStatus());
        card.setCvv(cardUpdateAdminRequest.getCvv());
        card.setBalance(cardUpdateAdminRequest.getBalance());
        card.setExpiryDate(cardUpdateAdminRequest.getExpiryDate());
        card.setOwner(cardUpdateAdminRequest.getOwner());

        Card updatedCard = cardRepository.save(card);
        return modelMapper.map(updatedCard, CardDto.class);
    }

    @Override
    public void deleteCard(UUID cardId) {
        cardRepository.deleteById(cardId);
    }

    @Override
    public BigDecimal getBalance(UUID cardId) {
        Card card = cardRepository.getCardById(cardId);
        return card.getBalance();
    }

    @Override
    public void transfer(CardTransferRequest cardTransferRequest) {
        UUID userSender = cardTransferRequest.getUserSenderId();
        UUID userReceiver = cardTransferRequest.getUserReceiverId();
        BigDecimal sentSum = cardTransferRequest.getSentSum();

        Card cardUserSender = cardRepository.getCardById(userSender);
        cardUserSender.setBalance(cardUserSender.getBalance().subtract(sentSum));

        Card cardReceiver = cardRepository.getCardById(userReceiver);
        cardReceiver.setBalance(cardReceiver.getBalance().add(sentSum));

        cardRepository.save(cardUserSender);
        cardRepository.save(cardReceiver);
    }

    @Override
    public void blockCard(UUID cardId) {
        Card card = cardRepository.getCardById(cardId);
        card.setStatus(CardStatus.BLOCKED);
    }

    @Override
    public void activateCard(UUID cardId) {
        Card card = cardRepository.getCardById(cardId);
        card.setStatus(CardStatus.ACTIVE);
    }
}
