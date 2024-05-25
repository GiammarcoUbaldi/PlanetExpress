package com.univaq.TestAgile.controller.api;

import com.univaq.TestAgile.model.Utente;
import com.univaq.TestAgile.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/api/modifica-utente")
public class ModificaUtenteController {

    @Autowired
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
    public String modificaUtente(Model model, Utente utente, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("success", "no");
            return "/Registrazione/modificaDati";
        }

        utenteRepository.save(utente);
        model.addAttribute("success", "ok");
        model.addAttribute("utente", utente);
        return "/Registrazione/modificaDati";
    }
}