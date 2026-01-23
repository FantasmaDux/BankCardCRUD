package com.example.bankcards.request;

import com.example.bankcards.entity.UserEntity;
import com.example.bankcards.enums.CardStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardUpdateAdminRequest {
    private String number;
    private LocalDate expiryDate;
    private String cvv;
    private BigDecimal balance;
    private CardStatus status;
    private UserEntity owner;
}
