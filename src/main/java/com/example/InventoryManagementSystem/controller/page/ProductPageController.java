package com.example.InventoryManagementSystem.controller.page;
import com.example.InventoryManagementSystem.dto.ProductDTO;
import com.example.InventoryManagementSystem.repository.InventoryLogRepository;
import com.example.InventoryManagementSystem.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ProductPageController {
    private final ProductService productService;
    private final InventoryLogRepository inventoryLogRepository;

    @GetMapping("/products")
    public String listProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("inventoryList", inventoryLogRepository.findAll());
        return "products";
    }

    @GetMapping("/products/create")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new ProductDTO());
        return "product_form";
    }

    @PostMapping("/products/save")
    public String createProduct(@ModelAttribute ProductDTO dto) {
        productService.createProduct(dto);
        return "redirect:/products";
    }

    @GetMapping("/products/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "product_form";
    }

    @PostMapping("/products/update/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute ProductDTO dto) {
        productService.updateProduct(id, dto);
        return "redirect:/products";
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }
}
