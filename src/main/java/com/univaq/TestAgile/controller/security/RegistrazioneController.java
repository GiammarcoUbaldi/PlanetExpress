package com.univaq.TestAgile.controller.security;

import com.univaq.TestAgile.model.Utente;
import com.univaq.TestAgile.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistrazioneController {
    @Autowired
    UtenteRepository utenteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register/user")
    public Utente createUser(@RequestBody Utente utente) {
        utente.setPassword(passwordEncoder.encode(utente.getPassword()));
        return utenteRepository.save(utente);
    }


    @PostMapping("/register/submit")
    public String registrazioneSubmit(@ModelAttribute Utente utente, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "/Registrazione/registrazione";
        }
        if (utenteRepository.existsByEmail(utente.getEmail())) {
            model.addAttribute("error", "Email già utilizzata, scegli un'altra email.");
            return "/Registrazione/registrazione";
        }
        //Aggiungo l utente al db
        this.createUser(utente);

        return "redirect:/";
    }

}
