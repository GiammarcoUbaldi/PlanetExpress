package com.univaq.TestAgile.controller;

import com.univaq.TestAgile.model.Utente;
import com.univaq.TestAgile.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
@EnableWebMvc
@RequestMapping("/api/login")
public class LoginController {
    @Autowired
    UtenteRepository utenteRepository;

    @GetMapping("/richiestaAccesso")
    public String getUtenteById(@RequestParam String nome, @RequestParam String password, Model model) {
        Utente utente = utenteRepository.findByNomeAndPassword(nome,password);
        System.out.println(utente);
        if(utente!=null){
            model.addAttribute("utente", utente);
            return "/login/dashboard";
        }else{
            return "redirect:/";
        }
    }

}
