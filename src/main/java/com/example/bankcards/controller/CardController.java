package com.example.bankcards.controller;

import com.example.bankcards.dto.ApiResponseDto;
import com.example.bankcards.dto.CardDto;
import com.example.bankcards.dto.CardFilterDto;
import com.example.bankcards.request.CardCreationRequest;
import com.example.bankcards.request.CardTransferRequest;
import com.example.bankcards.request.CardUpdateAdminRequest;
import com.example.bankcards.service.card.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api-prefix}/cards")
public class CardController {

    private final CardService cardService;


    @GetMapping("/admin/cards")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponseDto> getAllCardsAdmin(
            @PageableDefault Pageable pageable) {

        CardFilterDto filter = new CardFilterDto();

        Page<CardDto> cards = cardService.getAllCardsForAdmin(filter, pageable);
        return ResponseEntity.ok(new ApiResponseDto("All cards retrieved", cards));
    }

    @GetMapping("/admin/users/{username}/cards")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponseDto> getUserCardsAdmin(
            @PathVariable String username,
            @PageableDefault Pageable pageable) {

        CardFilterDto filter = new CardFilterDto();

        Page<CardDto> cards = cardService.getAllCardsByUsernameForAdmin(username, filter, pageable);
        return ResponseEntity.ok(new ApiResponseDto("User cards retrieved", cards));
    }

    @GetMapping("/my/cards")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ApiResponseDto> getMyCards(
            @PageableDefault Pageable pageable) {

        CardFilterDto filter = new CardFilterDto();

        Page<CardDto> cards = cardService.getAllCardsForUser(filter, pageable);
        return ResponseEntity.ok(new ApiResponseDto("Your cards retrieved", cards));
    }

    @GetMapping("/{cardId}")
    public ResponseEntity<ApiResponseDto> getCardById(@PathVariable UUID cardId) {
        CardDto cardDto = cardService.getCard(cardId);
        return ResponseEntity.ok(new ApiResponseDto("Card retrieved successfully", cardDto));
    }

    @PostMapping("")
    public ResponseEntity<ApiResponseDto> createCard(@RequestBody CardCreationRequest request) {

        CardDto cardDto = cardService.createCard(request);
        return ResponseEntity.ok(new ApiResponseDto("Card created successfully", cardDto));
    }

    @PutMapping("/{cardId}")
    public ResponseEntity<ApiResponseDto> updateCard(@RequestBody CardUpdateAdminRequest request,
                                                     @PathVariable UUID cardId) {
        CardDto cardDto = cardService.updateCard(cardId, request);
        return ResponseEntity.ok(new ApiResponseDto("Card updated successfully", cardDto));
    }

    @DeleteMapping("/{cardId}")
    public ResponseEntity<ApiResponseDto> deleteCard(@PathVariable UUID cardId) {
        cardService.deleteCard(cardId);
        return ResponseEntity.ok(new ApiResponseDto("Card deleted successfully", cardId));
    }

    @GetMapping("/{cardId}/balance")
    public ResponseEntity<ApiResponseDto> getBalance(@PathVariable UUID cardId) {
        BigDecimal balance = cardService.getBalance(cardId);
        return ResponseEntity.ok(new ApiResponseDto("Card balance retrieved successfully", balance));
    }

    @PutMapping("/transfer")
    public ResponseEntity<ApiResponseDto> transferMoney(@RequestBody CardTransferRequest request) {
        cardService.transfer(request);
        return ResponseEntity.ok(new ApiResponseDto("Transferred successfully", null));
    }

    @PutMapping({"/{cardId}/activation"})
    public ResponseEntity<ApiResponseDto> activateCard(@PathVariable UUID cardId) {
        cardService.activateCard(cardId);
        return ResponseEntity.ok(new ApiResponseDto("Card activated successfully", null));
    }


    @PutMapping({"/{cardId}/deactivation"})
    public ResponseEntity<ApiResponseDto> blockCard(@PathVariable UUID cardId) {
        cardService.blockCard(cardId);
        return ResponseEntity.ok(new ApiResponseDto("Card blocked successfully", null));
    }


}
