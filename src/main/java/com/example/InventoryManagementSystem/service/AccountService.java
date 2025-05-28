package com.example.InventoryManagementSystem.service;

import com.example.InventoryManagementSystem.dto.LoginRequestDTO;
import com.example.InventoryManagementSystem.dto.RegisterRequestDTO;
import com.example.InventoryManagementSystem.repository.AccountRepository;
import com.example.InventoryManagementSystem.dto.AccountDTO;
import com.example.InventoryManagementSystem.mapper.AccountMapper;
import com.example.InventoryManagementSystem.entity.Account;

import org.springframework.stereotype.Service;
import lombok.*;

@Service
@RequiredArgsConstructor
public class AccountService{

    private final AccountRepository accountRepository;

    public AccountDTO register(RegisterRequestDTO request) {
        Account account = Account.builder()
                .username(request.getUsername())
                .password(request.getPassword())  // nên mã hóa sau này
                .displayName(request.getDisplayName())
                .title(request.getTitle())
                .build();
        return AccountMapper.toDTO(accountRepository.save(account));
    }

    public AccountDTO login(LoginRequestDTO request) {
        Account account = accountRepository
                .findByUsernameAndPassword(request.getUsername(), request.getPassword())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));
        return AccountMapper.toDTO(account);
    }

    public AccountDTO getByUsername(String username) {
        Account account = accountRepository
                .findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        return AccountMapper.toDTO(account);
    }

    public Account getEntityByUsername(String username){
        return accountRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }
}
