package com.example.InventoryManagementSystem.service;

import com.example.InventoryManagementSystem.dto.ProductDTO;
import com.example.InventoryManagementSystem.entity.Product;
import com.example.InventoryManagementSystem.mapper.ProductMapper;
import com.example.InventoryManagementSystem.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO getProductById(Long id) {
        return productRepository.findById(id)
                .map(ProductMapper::toDTO)
                .orElse(null);
    }

    public ProductDTO saveProduct(ProductDTO dto) {
        Product saved = productRepository.save(ProductMapper.toEntity(dto));
        return ProductMapper.toDTO(saved);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public ProductDTO createProduct(ProductDTO dto) {
        // Đảm bảo ID là null để tránh cập nhật nhầm khi insert
        dto.setId(null);
        Product created = productRepository.save(ProductMapper.toEntity(dto));
        return ProductMapper.toDTO(created);
    }

    public ProductDTO updateProduct(Long id, ProductDTO dto) {
        return productRepository.findById(id)
                .map(existing -> {
                    // Cập nhật các trường cần thiết từ DTO
                    existing.setName(dto.getName());
                    existing.setCategory(dto.getCategory());
                    existing.setQuantity(dto.getQuantity());
                    Product updated = productRepository.save(existing);
                    return ProductMapper.toDTO(updated);
                })
                .orElse(null);
    }
}
