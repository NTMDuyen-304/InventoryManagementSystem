package com.example.InventoryManagementSystem.controller.page;

import com.example.InventoryManagementSystem.dto.AccountDTO;
import com.example.InventoryManagementSystem.dto.LoginRequestDTO;
import com.example.InventoryManagementSystem.mapper.AccountMapper;
import com.example.InventoryManagementSystem.service.AccountService;
import com.example.InventoryManagementSystem.entity.Account;
import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class LoginPageController {

    private final AccountService accountService;

    public LoginPageController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/loginPage")
    public String loginPage(Model model) {
        model.addAttribute("loginRequest", new LoginRequestDTO());
        return "login";
    }

    @PostMapping("/loginPage")
    public String doLogin(@ModelAttribute LoginRequestDTO loginRequest,
                          HttpSession session,
                          Model model) {
        try {
            Account account = accountService.getEntityByUsername(loginRequest.getUsername());
            if (account.getPassword().equals(loginRequest.getPassword())) {
                AccountDTO accountDTO = AccountMapper.toDTO(account);
                session.setAttribute("account", accountDTO);
                session.setAttribute("account", accountDTO);
                return "redirect:/home";
            } else {
                model.addAttribute("error", "Wrong username or password");
                return "login";
            }
        } catch (RuntimeException e) {
            model.addAttribute("error", "Wrong username or password");
            return "login";
        }
    }

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        AccountDTO account = (AccountDTO) session.getAttribute("account");
        if (account == null) {
            return "redirect:/loginPage";
        }
        model.addAttribute("accountDTO", account);
        return "dashboard"; 
    }
}