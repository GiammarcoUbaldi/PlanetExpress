package com.univaq.TestAgile.controller;


import com.univaq.TestAgile.controller.api.EventoController;
import com.univaq.TestAgile.controller.api.RiempiDbCotroller;
import com.univaq.TestAgile.controller.api.UtenteController;
import com.univaq.TestAgile.model.Evento;


import com.univaq.TestAgile.model.OrtoReferente;
import com.univaq.TestAgile.repository.PostRepository;

import com.univaq.TestAgile.model.Utente;
import com.univaq.TestAgile.repository.UtenteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {


    @Autowired
    EventoController eventoController;

    @Autowired
    UtenteController utenteController;

    @Autowired
    RiempiDbCotroller riempiDbCotroller;

    @Autowired
    PostRepository postRepository;

    @Autowired
    UtenteRepository utenteRepository;


    //Path della home
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("listaPost", postRepository.findAll());
        List<Evento> eventiRefe = eventoController.getEventiAccettati();
        model.addAttribute("EventiRefe", eventiRefe);
        return "/home/homePage";
    }

    //Autenticazione e Registrazione
    @GetMapping("/login")
    public String login() {
        return "/autenticazione/login";
    }

    @GetMapping("/registrazione")
    public String registrazioneForm(Utente utente) {
        return "/autenticazione/registrazione";
    }

    //Usato per lo sviluppo
    @GetMapping("/riempiDb")
    public String riempiDb() {
        riempiDbCotroller.inserisciDati();
        return "redirect:/";
    }

    //per la dashboard
    @GetMapping("/dashboard")
    public String mostraDashboard() {
        Utente utente = utenteController.getUtenteLoggato();
        if (utente != null) {
            switch (utente.getTipoUtente()) {
                case "USER":
                    return "redirect:/user/dashboard";
                case "ADMIN":
                    return "redirect:/admin/dashboard";
                case "REFERENTE":
                    return "redirect:/referente/dashboard";
            }
        }
        return "redirect:/";
    }


    //Evento Utente non Registrato
    @GetMapping("/no-user/mostraDettagliEvento/{id}")
    //@GetMapping("/mostraEventiRef/{id}")
    public String mostraEventiReferente(Model model, @PathVariable long id) {
        List<Evento> eventiRefe = eventoController.getEventiByIdRef(id);
        model.addAttribute("EventiRefe", eventiRefe);
        List<Evento> eventiRefeFuturi = eventoController.getEventiFuturiRef(id);
        model.addAttribute("EventiRefeFuturi", eventiRefeFuturi);
        List<Evento> eventiInSospeso = eventoController.getEventiAccettatiRef(id);
        model.addAttribute("EventiRefeInSospeso", eventiInSospeso);
        return "/referente/listaEventiReferente";
    }


    @GetMapping("/mostraDettagliEvento/{id}")
    public String mostraDettagliEvento(Model model, @PathVariable long id) {
        Evento evento = eventoController.getEventoById(id);
        model.addAttribute("datiDettagli", evento);
        return "/admin/dettagliEvento";
    }

    @GetMapping("/no-user/form-richiesta-orto")
    public String mostraFormRichiestaOrtoReferente(Model model) {
        return "/referente/formRichiestaOrtoReferete";
    }

}
