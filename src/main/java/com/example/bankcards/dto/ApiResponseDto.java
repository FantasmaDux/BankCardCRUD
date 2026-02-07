package com.example.bankcards.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponseDto {
    String message;
    Object data;
}
