package com.example.bankcards.request;

import com.example.bankcards.enums.RequestType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCardRequest {
    private UUID userId;
    private RequestType requestType;
    private UUID cardId;
}
