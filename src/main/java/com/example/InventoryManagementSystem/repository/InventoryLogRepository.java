package com.example.InventoryManagementSystem.repository;

import com.example.InventoryManagementSystem.entity.InventoryLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryLogRepository extends JpaRepository<InventoryLog, Long> {
    
}
