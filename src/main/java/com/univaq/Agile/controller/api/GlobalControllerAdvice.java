package com.univaq.Agile.controller.api;

import com.univaq.Agile.model.Utente;
import com.univaq.Agile.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {
    @Autowired
    UtenteRepository utenteRepository;

    //Per dare l'utente come modello globale per i template grafici
    @ModelAttribute
    public void addAuthenticatedUserToModel(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) principal;
                String email = userDetails.getUsername();
                Utente utente = utenteRepository.findByEmail(email);
                model.addAttribute("Utente", utente);
            }
        }
    }
}