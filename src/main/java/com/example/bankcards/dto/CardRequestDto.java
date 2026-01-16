package com.example.bankcards.dto;

import com.example.bankcards.entity.User;
import com.example.bankcards.enums.RequestStatus;
import com.example.bankcards.enums.RequestType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardRequestDto {
    RequestType requestType;
    RequestStatus requestStatus;
    User user;
}
