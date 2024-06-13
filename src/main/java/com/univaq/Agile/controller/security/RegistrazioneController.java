package com.univaq.Agile.controller.security;

import com.univaq.Agile.model.OrtoReferente;
import com.univaq.Agile.model.Utente;
import com.univaq.Agile.repository.OrtoReferenteRepository;
import com.univaq.Agile.repository.UtenteRepository;
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

    @Autowired
    OrtoReferenteRepository ortoReferenteRepository;

    @PostMapping("/register/user")
    public Utente createUser(@RequestBody Utente utente) {
        utente.setPassword(passwordEncoder.encode(utente.getPassword()));
        return utenteRepository.save(utente);
    }

    @PostMapping("/register/submit")
    public String registrazioneSubmit(@ModelAttribute Utente utente, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "/autenticazione/registrazione";
        }
        if (utenteRepository.existsByEmail(utente.getEmail())) {
            model.addAttribute("error", "Email già utilizzata, scegli un'altra email.");
            return "/autenticazione/registrazione";
        }
        //Aggiungo l utente al db
        this.createUser(utente);

        return "redirect:/";
    }


    @PostMapping("/register/referenteSubmit")
    public String registrazioneReferenteOrtoSubmit(
            @ModelAttribute Utente utente,
            @ModelAttribute OrtoReferente ortoReferente,
            BindingResult bindingResult,
            Model model) {

        // Validazione dei dati dell'utente
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "Errore nei dati dell'utente");
            return "/";
        }

        // Controllo se l'email esiste già
        if (utenteRepository.existsByEmail(utente.getEmail())) {
            model.addAttribute("error", "Email già utilizzata, scegli un'altra email.");
            return "/";
        }

        System.out.println("Orto");
        System.out.println(ortoReferente.getNome());
        // Settaggio del campo nomeReferente in OrtoReferente
        ortoReferente.setNomeReferente(utente.getNome() + " " + utente.getCognome());
        // Salvataggio dell'orto
        ortoReferenteRepository.save(ortoReferente);

        System.out.println("Utente");
        System.out.println(utente.getPassword());
        // Codifica della password
        utente.setPassword(passwordEncoder.encode(utente.getPassword()));
        utente.setOrtoOccupato(ortoReferente);
        // Salvataggio dell'utente
        utenteRepository.save(utente);

        ortoReferente.setUtente(utente);
        ortoReferenteRepository.save(ortoReferente);


        // Redirezione alla pagina di successo o home page
        return "redirect:/successPage";
    }
    @PostMapping("/add-richiesta-orto")
    public String createOrtoReferente(@ModelAttribute OrtoReferente ortoRichiesta) {
        ortoReferenteRepository.save(ortoRichiesta);
        return "redirect:/";
    }


}
