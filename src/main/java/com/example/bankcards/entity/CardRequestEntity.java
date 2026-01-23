package com.example.bankcards.entity;

import com.example.bankcards.enums.RequestStatus;
import com.example.bankcards.enums.RequestType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "card_requests")
@Getter
@Setter
public class CardRequestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    private UserEntity user;

    @ManyToOne
    private CardEntity card;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    @Enumerated(EnumType.STRING)
    private RequestType requestType;

    private LocalDateTime createdAt;
    private LocalDateTime processedAt;

    @ManyToOne
    private UserEntity processedBy;
}
