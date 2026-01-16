package com.example.bankcards.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardTransferRequest {
    UUID userSenderId;
    UUID userReceiverId;
    BigDecimal sentSum;
}
