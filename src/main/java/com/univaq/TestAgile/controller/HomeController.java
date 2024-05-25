package com.univaq.TestAgile.controller;


import com.univaq.TestAgile.controller.api.EventoController;
import com.univaq.TestAgile.controller.api.RiempiDbCotroller;
import com.univaq.TestAgile.model.Evento;


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
    RiempiDbCotroller riempiDbCotroller;

    @Autowired
    private PostRepository postRepository;

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
        return "/login/login";
    }

    @GetMapping("/registrazione")
    public String registrazioneForm(Utente utente) {
        return "/Registrazione/registrazione";
    }

    //Usato per lo sviluppo
    @GetMapping("/riempiDb")
    public String riempiDb() {
        riempiDbCotroller.inserisciDati();
        return "redirect:/";
    }

    //Evento Utente non Registrato
    @GetMapping("/no-user/mostraDettagliEvento/{id}")
    public String mostraDettagliEvento(Model model, @PathVariable long id) {
        Evento evento = eventoController.getEventoById(id);
        model.addAttribute("datiDettagli", evento);
        return "/admin/dettagliEvento";
    }

}
