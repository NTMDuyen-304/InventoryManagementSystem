package com.example.InventoryManagementSystem.controller.api;

import com.example.InventoryManagementSystem.dto.InventoryLogDTO;
import com.example.InventoryManagementSystem.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@CrossOrigin("*")
public class InventoryApiController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/logs")
    public List<InventoryLogDTO> getAllLogs() {
        return inventoryService.getAllLogs();
    }

    @PostMapping("/import")
    public InventoryLogDTO importProduct(@RequestParam Long productId, @RequestParam int amount) {
        return inventoryService.importProduct(productId, amount);
    }

    @PostMapping("/export")
    public InventoryLogDTO exportProduct(@RequestParam Long productId, @RequestParam int amount) {
        return inventoryService.exportProduct(productId, amount);
    }
}
