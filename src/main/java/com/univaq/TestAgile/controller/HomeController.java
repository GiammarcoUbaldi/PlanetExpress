package com.univaq.TestAgile.controller;


import com.univaq.TestAgile.model.Evento;


import com.univaq.TestAgile.model.Utente;
import com.univaq.TestAgile.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    EventoController eventoController;

    @Autowired
    RiempiDbCotroller riempiDbCotroller;


    @Autowired
    UtenteRepository utenteRepository;

    @GetMapping("/")
    public String index() {
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
    public String mostraEventiAdmin(Model model) {
        List<Evento> eventiAccettatiDb = eventoController.getEventiAccettati();
        List<Evento> eventiRifiutatiDb = eventoController.getEventiRifiutati();
        List<Evento> eventiInSospesoDb = eventoController.getEventiInSospeso();
        model.addAttribute("EventoAccettato", eventiAccettatiDb);
        model.addAttribute("EventoRifiutato", eventiRifiutatiDb);
        model.addAttribute("EventoInSospeso", eventiInSospesoDb);
        return "/admin/listaEventi";
    }
    /*
    @GetMapping("/mostraEventi")
    public String mostraEventiReferente(Model model) {
        List<Evento> eventiAccettatiDb = eventoController.getEventiProposti();

        return "/admin/listaEventi";
    }
    
     */

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


    @GetMapping("/utente/dashboardModifica")
    public String utenteDashboardModifica(Model model,@RequestParam("utenteId") Long utenteId) {
        Utente utente = utenteRepository.findById(utenteId).get();
        System.out.println(utente);
        if(utente!=null){
            model.addAttribute("utente",utente);
            return "/Registrazione/modificaDati";
        }
        return "redirect:/";
    }


}
