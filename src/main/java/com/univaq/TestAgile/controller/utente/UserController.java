package com.univaq.TestAgile.controller.utente;

import com.univaq.TestAgile.model.Utente;
import com.univaq.TestAgile.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UtenteRepository utenteRepository;

    @GetMapping("/creaPost")
    public String creaPost() {
        return "/Post/ScriviPost";
    }

    @GetMapping("/dashboardModifica")
    public String utenteDashboardModifica(Model model, @RequestParam("utenteId") Long utenteId) {
        Utente utente = utenteRepository.findById(utenteId).get();
        System.out.println(utente);
        if (utente != null) {
            model.addAttribute("utente", utente);
            return "/Registrazione/modificaDati";
        }
        return "redirect:/";
    }
}
