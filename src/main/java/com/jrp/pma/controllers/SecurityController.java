package com.jrp.pma.controllers;

import com.jrp.pma.dao.UserAccountRepository;
import com.jrp.pma.entities.UserAccount;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SecurityController {

    final
    UserAccountRepository userAccountRepository;

    final
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public SecurityController(BCryptPasswordEncoder bCryptPasswordEncoder,
                              UserAccountRepository userAccountRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userAccountRepository = userAccountRepository;
    }

    @GetMapping("/register")
    public String register(Model model) {
        UserAccount userAccount = new UserAccount();
        model.addAttribute("userAccount", userAccount);
        return "security/register";
    }

    @PostMapping("/register/save")
    public String saveUser(Model model, UserAccount user) {
        user.setRole("ROLE_USER");
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userAccountRepository.save(user);
        return "redirect:/";
    }
}
