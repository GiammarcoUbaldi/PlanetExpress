package com.univaq.TestAgile.controller;


import com.univaq.TestAgile.model.Evento;


import com.univaq.TestAgile.repository.PostRepository;

import com.univaq.TestAgile.model.Utente;
import com.univaq.TestAgile.repository.UtenteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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


    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("listaPost", postRepository.findAll());
        List<Evento> eventiRefe = eventoController.getEventiAccettati();
        model.addAttribute("EventiRefe", eventiRefe);
        return "/home/homePage";
    }

    @GetMapping("/login")
    public String login() {
        return "/login/login";
    }


    @GetMapping("/admin/a")
    public String adminDashboard() {
        return "/Post/ScriviPost";
    }

    @GetMapping("/user/a")
    public String dashboard() {
        return "/Post/ScriviPost";
    }
//    @GetMapping("/user")
//    public String dashboard() {
//        return "login/dashboard";
//    }


    @GetMapping("/riempiDb")
    public String riempiDb() {
        riempiDbCotroller.inserisciDati();
        return "redirect:/";
    }

    @GetMapping("/mostraEventi")
    public String mostraEventiAdmin(Model model) {
        List<Evento> eventiAccettatiDb = eventoController.getEventiAccettati();
        List<Evento> eventiRifiutatiDb = eventoController.getEventiRifiutati();
        List<Evento> eventiInSospesoDb = eventoController.getEventiInSospeso();
        model.addAttribute("EventoAccettato", eventiAccettatiDb);
        model.addAttribute("EventoRifiutato", eventiRifiutatiDb);
        model.addAttribute("EventoInSospeso", eventiInSospesoDb);
        return "/admin/listaEventi";
    }

    @GetMapping("/mostraEventiRef/{id}")
    public String mostraEventiReferente(Model model, @PathVariable long id) {
        List<Evento> eventiRefe = eventoController.getEventiByIdRef(id);
        model.addAttribute("EventiRefe", eventiRefe);
        return "/referente/listaEventiReferente";
    }


    @GetMapping("/mostraDettagliEvento/{id}")
    public String mostraDettagliEvento(Model model, @PathVariable long id) {
        Evento evento = eventoController.getEventoById(id);
        model.addAttribute("datiDettagli", evento);
        return "/admin/dettagliEvento";
    }


    @GetMapping("/utente/dashboardModifica")
    public String utenteDashboardModifica(Model model, @RequestParam("utenteId") Long utenteId) {
        Utente utente = utenteRepository.findById(utenteId).get();
        System.out.println(utente);
        if (utente != null) {
            model.addAttribute("utente", utente);
            return "/Registrazione/modificaDati";
        }
        return "redirect:/";
    }

    @GetMapping("/creaPost")
    public String creaPost() {
        return "/Post/ScriviPost";
    }
}
