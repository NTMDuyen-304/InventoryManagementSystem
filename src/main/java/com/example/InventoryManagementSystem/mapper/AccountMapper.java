package com.example.InventoryManagementSystem.mapper;

import com.example.InventoryManagementSystem.dto.AccountDTO;
import com.example.InventoryManagementSystem.entity.Account;

public class AccountMapper {

    // Entity → DTO
    public static AccountDTO toDTO(Account account) {
        return AccountDTO.builder()
                .id(account.getId())
                .username(account.getUsername())
                .displayName(account.getDisplayName())
                .title(account.getTitle())
                .build();
    }

    // DTO → Entity
    public static Account toEntity(AccountDTO dto) {
        return Account.builder()
                .id(dto.getId())
                .username(dto.getUsername())
                .displayName(dto.getDisplayName())
                .title(dto.getTitle())
                .build();
    }
}