package com.univaq.TestAgile.controller;

import com.univaq.TestAgile.model.Cliente;

import com.univaq.TestAgile.model.Evento;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    EventoController eventoController;

    @Autowired
    RiempiDbCotroller riempiDbCotroller;

    @GetMapping("/")
    public String index() {
        return "/home/index";
    }

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
        model.addAttribute("testDatiEsempio", eventiInSospesoDb);
        return "/admin/listaEventi";
    }

    @GetMapping("/form-richiesta-orto-referente")
    public String mostraFormRichiestaOrtoReferente(Model model) {
        return "/referente/formRichiestaOrtoReferete";
    }

}
