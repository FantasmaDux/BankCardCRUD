package com.example.bankcards.entity;

import com.example.bankcards.enums.RequestStatus;
import com.example.bankcards.enums.RequestType;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "card_requests")
public class CardRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Card card;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    @Enumerated(EnumType.STRING)
    private RequestType requestType;

    private LocalDateTime createdAt;
    private LocalDateTime processedAt;

    @ManyToOne
    private User processedBy;
}
