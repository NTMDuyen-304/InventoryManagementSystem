package com.example.InventoryManagementSystem.mapper;

import com.example.InventoryManagementSystem.dto.InventoryLogDTO;
import com.example.InventoryManagementSystem.entity.InventoryLog;
import com.example.InventoryManagementSystem.entity.Product;

public class InventoryLogMapper {

    //entity->dto
    public static InventoryLogDTO toDTO(InventoryLog log) {
        if (log == null) return null;

        return new InventoryLogDTO(
                log.getId(),
                log.getProduct() != null ? log.getProduct().getId() : null,
                log.getProduct() != null ? log.getProduct().getName() : null,
                log.getAction(),
                log.getAmount(),
                log.getCreatedAt()
        );
    }

    public static InventoryLog toEntity(InventoryLogDTO dto){
        if (dto == null) return null;

        InventoryLog log = new InventoryLog();
        log.setId(dto.getId());
        log.setAction(dto.getAction());
        log.setAmount(dto.getAmount());
        log.setCreatedAt(dto.getCreatedAt());

        if (dto.getProductId() != null) {
            Product product = new Product();
            product.setId(dto.getProductId());
            // Không cần set name vì chỉ cần id để liên kết (nếu cần set name thì dùng khi tạo mới Product)
            log.setProduct(product);
        }

        return log;
    }
}
