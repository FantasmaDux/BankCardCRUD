package com.example.bankcards.repository;


import com.example.bankcards.entity.CardRequestEntity;
import com.example.bankcards.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CardRequestRepository extends JpaRepository<CardRequestEntity, UUID> {
    Page<CardRequestEntity> findByUser(UserEntity user, Pageable pageable);
}
