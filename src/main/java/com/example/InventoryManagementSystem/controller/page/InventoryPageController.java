package com.example.InventoryManagementSystem.controller.page;
import com.example.InventoryManagementSystem.dto.InventoryLogDTO;
import com.example.InventoryManagementSystem.service.InventoryService;
import com.example.InventoryManagementSystem.service.ProductService;
import lombok.RequiredArgsConstructor;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class InventoryPageController {
    private final InventoryService logService;
    private final ProductService productService;

    @GetMapping("/movements")
    public String listLogs(Model model) {
        model.addAttribute("movements", logService.getAllLogs());
        return "movements";
    }
  


    @GetMapping("/movements/record")
    public String showLogForm(Model model) {
        model.addAttribute("movement", new InventoryLogDTO());
        model.addAttribute("products", productService.getAllProducts());
        return "movement_form";
    }

     @PostMapping("/movements/save")
    public String createLog(@ModelAttribute InventoryLogDTO dto) {
        logService.createLog(dto);
        return "redirect:/movements";
    }
}
