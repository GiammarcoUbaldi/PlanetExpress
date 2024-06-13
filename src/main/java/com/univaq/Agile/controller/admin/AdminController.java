package com.univaq.Agile.controller.admin;

import com.univaq.Agile.controller.api.EventoController;
import com.univaq.Agile.controller.api.OrtoReferenteController;
import com.univaq.Agile.model.Evento;
import com.univaq.Agile.model.OrtoReferente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    OrtoReferenteController ortoReferenteController;
    @Autowired
    EventoController eventoController;


    @GetMapping("/richieste-orto-referente")
    public String mostraRichiesteOrtoReferente(Model model) {
        List<OrtoReferente> recuperoDatiIndefinite = ortoReferenteController.getRichiesteOrtiIndefinite();
        List<OrtoReferente> recuperoDatiAccettate = ortoReferenteController.getRichiesteOrtiAccettate();
        List<OrtoReferente> recuperoDatiRifiutate = ortoReferenteController.getRichiesteOrtiRifiutate();
        model.addAttribute("datiRichiesteReferenteIndefinite", recuperoDatiIndefinite);
        model.addAttribute("datiRichiesteReferenteAccettate", recuperoDatiAccettate);
        model.addAttribute("datiRichiesteReferenteRifiutate", recuperoDatiRifiutate);
        return "/admin/richieste-orto-referente";
    }


    @GetMapping("/dashboard")
    public String dashboard() {
        return "admin/dashboardAdmin";
    }

    //Evento
    @GetMapping("/mostraDettagliEvento/{id}")
    public String mostraDettagliEvento(Model model, @PathVariable long id) {
        Evento evento = eventoController.getEventoById(id);
        model.addAttribute("datiDettagli", evento);
        return "admin/dettagliEventoAdmin";
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

}
