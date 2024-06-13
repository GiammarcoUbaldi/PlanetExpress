package com.univaq.Agile.controller.api;

import com.univaq.Agile.model.OrtoReferente;
import com.univaq.Agile.model.Utente;
import com.univaq.Agile.model.Zolla;
import com.univaq.Agile.repository.ZollaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/zolla")
public class ZollaController {

    @Autowired
    private OrtoReferenteController ortoReferenteController;
    @Autowired
    private ZollaRepository zollaRepository;

    @GetMapping("/getListZolleByIdOrto/{idOrto}")
    public List<Zolla> getListZolleByIdOrto(@PathVariable long idOrto) {
        OrtoReferente ortoReferente = ortoReferenteController.getOrtoById(idOrto);
        return zollaRepository.findByOrto(ortoReferente);
    }

    @GetMapping("/getListZolleByOrto/{Orto}")
    public List<Zolla> getListZolleByOrto(@PathVariable OrtoReferente Orto) {
        return zollaRepository.findByOrto(Orto);
    }


    public List<Zolla> getListZolleByOrtoAndUtente(OrtoReferente ortoReferente, Utente utente) {
        return zollaRepository.findByOrtoAndUtente(ortoReferente,utente);
    }
}
