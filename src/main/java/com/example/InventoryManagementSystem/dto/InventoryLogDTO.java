package com.example.InventoryManagementSystem.dto;
import lombok.*;

//import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
       
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryLogDTO {
    private Long id;
    private Long productId;
    private String productName; 
    private String action;
    private Integer amount;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;
    
}