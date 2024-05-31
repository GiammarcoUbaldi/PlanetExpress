package com.univaq.TestAgile.controller.referente;

import com.univaq.TestAgile.controller.api.EventoController;
import com.univaq.TestAgile.controller.api.OrtoReferenteController;
import com.univaq.TestAgile.controller.api.UtenteController;
import com.univaq.TestAgile.controller.api.ZollaController;
import com.univaq.TestAgile.model.Evento;
import com.univaq.TestAgile.model.OrtoReferente;
import com.univaq.TestAgile.model.Utente;
import com.univaq.TestAgile.model.Zolla;
import com.univaq.TestAgile.repository.OrtoReferenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/referente")
public class ReferenteController {
    @Autowired
    OrtoReferenteController ortoReferenteController;

    @Autowired
    ZollaController zollaController;

    @Autowired
    EventoController eventoController;

    @Autowired
    OrtoReferenteRepository ortoReferenteRepository;

    @Autowired
    UtenteController utenteController;


    @GetMapping("/orto/{id}")
    public String mostraOrtoReferente(Model model, @PathVariable long id) {
        Utente utente = utenteController.getUtenteLoggato();
        if (utente != null) {
            if (ortoReferenteRepository.findOrtoReferenteByUtenteAndId(utente, id) != null) {
                OrtoReferente ortoReferente = ortoReferenteController.getOrtoById(id);
                List<Zolla> zolle = zollaController.getListZolleByOrto(ortoReferente);
                model.addAttribute("ortoReferente", ortoReferente);
                model.addAttribute("zolle", zolle);
                return "/referente/orto/visualizzaOrtoReferente";
            }
        }

        return "redirect:/login";

    }



    @GetMapping("/mostraEventiRef/{id}")
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
        return "/referente/dettagliEventoReferente";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        Utente utente = utenteController.getUtenteLoggato();
        if (utente != null) {
            List<OrtoReferente> ortiDelReferente = ortoReferenteRepository.findByUtente(utente);
            if (!ortiDelReferente.isEmpty())
                model.addAttribute("ortoDelReferente", ortiDelReferente.get(0));
        }
        return "referente/dashboardReferente";
    }


    @GetMapping("/form-richiesta-orto")
    public String mostraFormRichiestaOrtoReferente(Model model) {
        return "/referente/formRichiestaOrtoReferete";
    }

    @GetMapping("/form-richiesta-evento")
    public String mostraFormRichiestaEventoReferente(Model model) {
        return "/referente/formRichiestaEventoReferente";
    }


}
