package com.univaq.Agile.controller.api;

import com.univaq.Agile.model.Utente;
import com.univaq.Agile.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/utenti")
public class UtenteController {

    @Autowired
    private UtenteRepository utenteRepository;
    @GetMapping
    public List<Utente> getAllUtenti() {
        return (List<Utente>) utenteRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Utente> getUtenteById(@PathVariable Long id) {
        return utenteRepository.findById(id);
    }

    @GetMapping("/email/{id}")
    public Utente getUtenteByEmail(@PathVariable String id) {
        return utenteRepository.findByEmail(id);
    }

    @PostMapping("/utente/registrazione")
    public Utente registraUtente(@RequestBody Utente utente) {
        return utenteRepository.save(utente);
    }

    @PostMapping
    public Utente createUtente(@RequestBody Utente utente) {
        return utenteRepository.save(utente);
    }

    @PostMapping("/nuovo")
    public String creaUtente(@ModelAttribute Utente utente) {
        utenteRepository.save(utente);
        return "redirect:/db";
    }

    @DeleteMapping("/{id}")
    public void deleteUtente(@PathVariable Long id) {
        utenteRepository.deleteById(id);
    }

    /* Endpoint per ottenere utenti per tipo
    @GetMapping("/by-type/{tipo}")
    public List<Utente> getUtentiByType(@PathVariable String tipo) {
        return utenteRepository.findByTipo(tipo);
    }

     */
    public Utente getUtenteLoggato() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            String username = ((UserDetails) authentication.getPrincipal()).getUsername();
            return utenteRepository.findByEmail(username);
        }
        return null;
    }
}
