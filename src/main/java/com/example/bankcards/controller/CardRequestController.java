package com.example.bankcards.controller;

import com.example.bankcards.service.cardRequest.CardRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api-prefix}/cardRequests")
public class CardRequestController {

    private final CardRequestService cardRequestService;


}
