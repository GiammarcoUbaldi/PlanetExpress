package com.univaq.TestAgile.controller.utente;

import com.univaq.TestAgile.controller.api.EventoController;
import com.univaq.TestAgile.controller.api.UtenteController;
import com.univaq.TestAgile.model.Evento;
import com.univaq.TestAgile.model.Utente;
import com.univaq.TestAgile.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UtenteController utenteController;

    @Autowired
    EventoController eventoController;

    @Autowired
    UtenteRepository utenteRepository;

    @GetMapping("/dashboard")
    public String dashboard() {
        return "utente/dashboardUtente";
    }

    @GetMapping("/mostraEventiUser")
    public String mostraEventiUtente(Model model) {
        Utente user = utenteController.getUtenteLoggato();
        List<Evento> eventiTotali = eventoController.getEventi3User();
        model.addAttribute("EventiTotali", eventiTotali);
        List<Evento> eventiCitta = eventoController.getEventiCitta(user.getNazione());
        model.addAttribute("EventiCitta", eventiCitta);
        //List<Evento> eventiPrenotati = eventoController.getEventiPrenotati(id);
        //model.addAttribute("EventiPrenotati", eventiPrenotati);
        return "utente/listaEventiUser";
    }

    @GetMapping("/mostraDettagliEvento/{id}")
    public String mostraDettagliEvento(Model model, @PathVariable long id) {
        Evento evento = eventoController.getEventoById(id);
        model.addAttribute("datiDettagli", evento);
        return "utente/dettagliEventoUser";
    }


}
