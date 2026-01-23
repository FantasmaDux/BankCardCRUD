package com.example.bankcards.dto;

import com.example.bankcards.entity.CardEntity;
import com.example.bankcards.entity.RoleEntity;
import lombok.Data;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Data
public class UserDto {

    private UUID id;
    private String name;
    private String surname;
    private Collection<RoleEntity> roles = new HashSet<>();
    private List<CardEntity> cards;
}
