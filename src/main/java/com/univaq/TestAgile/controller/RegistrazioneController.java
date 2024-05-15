package com.univaq.TestAgile.controller;

import com.univaq.TestAgile.model.Utente;
import com.univaq.TestAgile.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/registrazione")
public class RegistrazioneController {

    private final UtenteRepository utenteRepository;

    @Autowired
    public RegistrazioneController(UtenteRepository utenteRepository) {
        this.utenteRepository = utenteRepository;
    }

    @GetMapping("/form")
    public String registrazioneForm(Utente utente) {
        return "/registrazione/form";
    }

    @PostMapping("/submit")
    public String registrazioneSubmit( Utente utente, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "/registrazione/form";
        }

        if (utenteRepository.existsByEmail(utente.getEmail())) {
            model.addAttribute("error", "Email già utilizzata, scegli un'altra email.");
            return "/registrazione/form";
        }

        utenteRepository.save(utente);
        model.addAttribute("utente", utente);
        return "/registrazione/conferma";
    }
}