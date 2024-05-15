package com.univaq.TestAgile.controller;

import com.univaq.TestAgile.model.OrtoReferente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/referente")
public class ReferenteController {
    @Autowired
    OrtoReferenteController ortoReferenteController;

    @GetMapping("/orto/{id}")
    public String mostraOrtoReferente(Model model, @PathVariable long id) {
        OrtoReferente ortoReferente = ortoReferenteController.getEventoById(id);
        model.addAttribute("ortoReferenteZolle", ortoReferente);
        return "/referente/orto/visualizzaOrtoReferente";
    }

    @GetMapping("/form-richiesta-orto")
    public String mostraFormRichiestaOrtoReferente(Model model) {
        return "/referente/formRichiestaOrtoReferete";
    }


}
