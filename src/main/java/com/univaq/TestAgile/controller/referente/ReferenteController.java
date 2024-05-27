package com.univaq.TestAgile.controller.referente;

import com.univaq.TestAgile.controller.api.EventoController;
import com.univaq.TestAgile.controller.api.OrtoReferenteController;
import com.univaq.TestAgile.controller.api.ZollaController;
import com.univaq.TestAgile.model.Evento;
import com.univaq.TestAgile.model.OrtoReferente;
import com.univaq.TestAgile.model.Zolla;
import org.springframework.beans.factory.annotation.Autowired;
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


    @GetMapping("/orto/{id}")
    public String mostraOrtoReferente(Model model, @PathVariable long id) {
        OrtoReferente ortoReferente = ortoReferenteController.getOrtoById(id);
        List<Zolla> zolle = zollaController.getListZolleByOrto(ortoReferente);
        model.addAttribute("ortoReferente", ortoReferente);
        model.addAttribute("zolle", zolle);
        return "/referente/orto/visualizzaOrtoReferente";
    }



    @GetMapping("/mostraEventiRef/{id}")
    public String mostraEventiReferente(Model model, @PathVariable long id) {
        List<Evento> eventiRefe = eventoController.getEventiByIdRef(id);
        model.addAttribute("EventiRefe", eventiRefe);
        return "/referente/listaEventiReferente";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
//        List<OrtoReferente> ortiReferente = ortoReferenteController.get
//        model.addAttribute("ortiDelReferente", ortiReferente);
        return "referente/dashboardReferente";
    }


}
