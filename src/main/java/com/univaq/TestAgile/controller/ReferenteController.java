package com.univaq.TestAgile.controller;

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

    @GetMapping("/orto/{id}")
    public String mostraOrtoReferente(Model model, @PathVariable long id) {
        OrtoReferente ortoReferente = ortoReferenteController.getEventoById(id);
        List<Zolla> zolle = zollaController.getListZolleByOrto(ortoReferente);
        model.addAttribute("ortoReferente", ortoReferente);
        model.addAttribute("zolle", zolle);
        return "/referente/orto/visualizzaOrtoReferente";
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
