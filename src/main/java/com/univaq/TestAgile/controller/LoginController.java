package com.univaq.TestAgile.controller;

import com.univaq.TestAgile.model.Utente;
import com.univaq.TestAgile.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
public class LoginController {
    @Autowired
    UtenteRepository utenteRepository;

    @GetMapping("/richiestaAccesso")
    public String getUtenteById(@RequestParam String email, @RequestParam String password, Model model) {
        Utente utente = utenteRepository.findByEmailAndPassword(email,password);
        if(utente!=null){
            model.addAttribute("utente", utente);
            model.addAttribute("loggato", true);
            return "/login/dashboard";
        }else{
            model.addAttribute("error", "Utente non presente, Riprova");
            return "/login/login";
        }
    }

    @Autowired
    private PasswordEncoder passwordEncoder;
    @PostMapping("/register/user")
    public Utente createUser(@RequestBody Utente user) {
        System.out.println("Ci arrivo");
        System.out.println(user.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println("Ci arrivo22");
        System.out.println(user.getPassword());
        return utenteRepository.save(user);
    }

}
