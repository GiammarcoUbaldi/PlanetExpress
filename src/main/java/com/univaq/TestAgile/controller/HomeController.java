package com.univaq.TestAgile.controller;


import com.univaq.TestAgile.model.Evento;


import com.univaq.TestAgile.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    EventoController eventoController;

    @Autowired
    RiempiDbCotroller riempiDbCotroller;

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("listaPost", postRepository.findAll());
        return "/home/homePage";
    }

    @GetMapping("/login")
    public String login() {return "/login/login";}

    @GetMapping("/riempiDb")
    public String riempiDb() {
        riempiDbCotroller.inserisciDati();
        return "redirect:/";
    }

    @GetMapping("/mostraEventi")
    public String mostraEventi(Model model) {
        List<Evento> eventiAccettatiDb = eventoController.getEventiAccettati();
        List<Evento> eventiRifiutatiDb = eventoController.getEventiRifiutati();
        List<Evento> eventiInSospesoDb = eventoController.getEventiInSospeso();
        model.addAttribute("EventoAccettato", eventiAccettatiDb);
        model.addAttribute("EventoRifiutato", eventiRifiutatiDb);
        model.addAttribute("EventoInSospeso", eventiInSospesoDb);
        return "/admin/listaEventi";
    }

    @GetMapping("/mostraDettagliEvento/{id}")
    public String mostraDettagliEvento(Model model, @PathVariable long id) {
        Evento evento = eventoController.getEventoById(id);
        model.addAttribute("datiDettagli", evento);
        return "/admin/dettagliEvento";
    }

    @GetMapping("/form-richiesta-orto-referente")
    public String mostraFormRichiestaOrtoReferente(Model model) {
        return "/referente/formRichiestaOrtoReferete";
    }

    @GetMapping("/creaPost")
    public String creaPost() {
        return "/Post/ScriviPost";
    }
}
