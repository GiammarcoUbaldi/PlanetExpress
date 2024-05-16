package com.univaq.TestAgile.controller;

import ch.qos.logback.core.model.Model;
import com.univaq.TestAgile.model.Utente;
import com.univaq.TestAgile.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
/*
@Controller
@RequestMapping("/api/modifica-utente")
public class ModificaUtenteController {

    private final UtenteRepository utenteRepository;

    @Autowired
    public ModificaUtenteController(UtenteRepository utenteRepository) {
        this.utenteRepository = utenteRepository;
    }

    @GetMapping
    public String mostraModificaUtente(@RequestParam Long id, Model model) {
        Utente utente = utenteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Utente non trovato"));
      //  model.addAttribute("utente", utente);
        return "/modifica-utente/form";
    }


    @PostMapping("/submit")
    public String modificaUtente(@Valid Utente utente, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "/modifica-utente/form";
        }

        utenteRepository.save(utente);
        model.addAttribute("utente", utente);
        return "/modifica-utente/conferma";
    }


}
*/