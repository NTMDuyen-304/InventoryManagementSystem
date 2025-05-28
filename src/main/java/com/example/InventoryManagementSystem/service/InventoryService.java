package com.example.InventoryManagementSystem.service;

import com.example.InventoryManagementSystem.dto.InventoryLogDTO;
import com.example.InventoryManagementSystem.entity.InventoryLog;
import com.example.InventoryManagementSystem.entity.Product;
import com.example.InventoryManagementSystem.mapper.InventoryLogMapper;
import com.example.InventoryManagementSystem.repository.InventoryLogRepository;
import com.example.InventoryManagementSystem.repository.ProductRepository;

import org.springframework.transaction.annotation.Transactional; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date; // Dùng java.util.Date cho log.setCreatedAt(new java.util.Date());
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryService {

    @Autowired
    private InventoryLogRepository logRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public List<InventoryLogDTO> getAllLogs() {
        return logRepository.findAll()
                .stream()
                .map(InventoryLogMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public InventoryLogDTO importProduct(Long productId, int amount) {
        return updateStock(productId, amount, "IMPORT");
    }

    @Transactional
    public InventoryLogDTO exportProduct(Long productId, int amount) {
        return updateStock(productId, amount, "EXPORT");
    }

    @Transactional // Đảm bảo phương thức này cũng được quản lý giao dịch
    private InventoryLogDTO updateStock(Long productId, int amount, String action) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product == null) {
            // Có thể ném một ngoại lệ cụ thể hơn hoặc trả về Optional
            throw new RuntimeException("Product with ID " + productId + " not found.");
        }

        if (action.equals("IMPORT")) {
            product.setQuantity(product.getQuantity() + amount);
        } else if (action.equals("EXPORT")) {
            // Kiểm tra số lượng tồn kho trước khi xuất
            if (product.getQuantity() < amount) {
                throw new RuntimeException("Not enough stock for product " + product.getName() + ". Available: " + product.getQuantity() + ", Requested: " + amount);
            }
            product.setQuantity(product.getQuantity() - amount);
        } else {
            throw new IllegalArgumentException("Invalid action type. Only IMPORT or EXPORT are allowed.");
        }

        productRepository.save(product); // Lưu thay đổi vào bảng Product

        InventoryLog log = new InventoryLog();
        log.setProduct(product); // Product entity đã được managed bởi JPA
        log.setAction(action);
        log.setAmount(amount);
        log.setCreatedAt(new Date()); // Sử dụng java.util.Date

        return InventoryLogMapper.toDTO(logRepository.save(log)); // Lưu InventoryLog vào database
    }

    @Transactional // Đảm bảo phương thức này cũng được quản lý giao dịch
    public InventoryLogDTO createLog(InventoryLogDTO dto) {
        if (dto.getProductId() == null || dto.getAction() == null || dto.getAmount() == null || dto.getAmount() <= 0) {
            throw new IllegalArgumentException("Invalid inventory log data");
        }

        String action = dto.getAction().toUpperCase();
        Long productId = dto.getProductId();
        int amount = dto.getAmount();

        // Các phương thức importProduct và exportProduct đã được đánh dấu @Transactional
        // và chúng gọi updateStock (cũng @Transactional)
        // Vì vậy, toàn bộ chuỗi cuộc gọi này sẽ nằm trong một giao dịch.
        if (action.equals("IMPORT")) {
            return importProduct(productId, amount);
        } else if (action.equals("EXPORT")) {
            // Logic kiểm tra tồn kho đã có trong updateStock
            // Nhưng việc kiểm tra trước ở đây cũng tốt để ném lỗi sớm hơn
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            if (product.getQuantity() < amount) {
                throw new RuntimeException("Not enough stock to export");
            }
            return exportProduct(productId, amount);
        } else {
            throw new IllegalArgumentException("Invalid action type. Only IMPORT or EXPORT are allowed.");
        }
    }
}