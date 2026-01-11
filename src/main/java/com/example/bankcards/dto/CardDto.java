package com.example.bankcards.dto;

import com.example.bankcards.entity.User;
import com.example.bankcards.enums.CardStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class CardDto {

    private UUID id;
    private String number;
    private String expiryDate;
    private String cvv;
    private BigDecimal balance;
    private CardStatus status;
    private User owner;
}
