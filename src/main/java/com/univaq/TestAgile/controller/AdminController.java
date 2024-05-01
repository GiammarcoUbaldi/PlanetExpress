package com.univaq.TestAgile.controller;

import com.univaq.TestAgile.model.Cliente;
import com.univaq.TestAgile.model.OrtoReferente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    OrtoReferenteController ortoReferenteController;

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


}
