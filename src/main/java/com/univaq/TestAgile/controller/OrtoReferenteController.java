package com.univaq.TestAgile.controller;

import com.univaq.TestAgile.model.Evento;
import com.univaq.TestAgile.model.OrtoReferente;
import com.univaq.TestAgile.repository.OrtoReferenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/api/orto-referente")
public class OrtoReferenteController {
    @Autowired
    private OrtoReferenteRepository ortoReferenteRepository;

    @GetMapping("/all")
    public List<OrtoReferente> getAllRichiesteOrti() {
        return (List<OrtoReferente>) ortoReferenteRepository.findAll();
    }

    @GetMapping("/indefinite")
    public List<OrtoReferente> getRichiesteOrtiIndefinite() {
        return ortoReferenteRepository.findByStato("indefinito");
    }
    @GetMapping("/accettate")
    public List<OrtoReferente> getRichiesteOrtiAccettate() {
        return ortoReferenteRepository.findByStato("accettato");
    }
    @GetMapping("/rifiutate")
    public List<OrtoReferente> getRichiesteOrtiRifiutate() {
        return ortoReferenteRepository.findByStato("rifiutato");
    }


    @PostMapping("/add-richiesta-orto")
    public String createEvento(@ModelAttribute OrtoReferente ortoRichiesta) {
        ortoReferenteRepository.save(ortoRichiesta);
        return "redirect:/";
    }

    @GetMapping("/accetta-richiesta/{id}/{stato}")
    public String accettaRichiestaOrto(@PathVariable Long id,@PathVariable String stato) {
        System.out.println("Richiesta Accettata"+id);
        OrtoReferente temp= ortoReferenteRepository.findById(id).get();
        temp.setStato(stato);
        ortoReferenteRepository.save(temp);
        return "redirect:/admin/richieste-orto-referente";
    }

    @GetMapping("/get/{id}/")
    public OrtoReferente getEventoById(@PathVariable long id) {
        return ortoReferenteRepository.findById(id).get();
    }

}
