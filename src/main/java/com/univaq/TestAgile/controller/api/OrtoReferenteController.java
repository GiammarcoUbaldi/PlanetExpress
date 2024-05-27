package com.univaq.TestAgile.controller.api;

import com.univaq.TestAgile.model.Evento;
import com.univaq.TestAgile.model.OrtoReferente;
import com.univaq.TestAgile.model.Zolla;
import com.univaq.TestAgile.repository.OrtoReferenteRepository;
import com.univaq.TestAgile.repository.ZollaRepository;
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

    @Autowired
    private ZollaRepository zollaRepository;

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



    @GetMapping("/accetta-richiesta/{id}/{stato}")
    public String accettaRichiestaOrto(@PathVariable Long id, @PathVariable String stato) {
        System.out.println("Richiesta Accettata" + id);
        OrtoReferente tempOrto = ortoReferenteRepository.findById(id).get();
        tempOrto.setStato(stato);
        //Riempio l'orto con le zolle
        insertZolleVuote(tempOrto);

        ortoReferenteRepository.save(tempOrto);


        return "redirect:/admin/richieste-orto-referente";
    }

    @GetMapping("/get/{id}/")
    public OrtoReferente getOrtoById(@PathVariable long id) {
        return ortoReferenteRepository.findById(id).get();
    }

//    @GetMapping("/getAll/{id}/")
//    public List<OrtoReferente> getAllOrtoById(@PathVariable long id) {
//        return ortoReferenteRepository.find;
//    }

    @GetMapping("/insertZolleVuote/{orto}/")
    public void insertZolleVuote(@PathVariable OrtoReferente orto) {
        int dimensioneX = orto.getDimensioneX();
        int dimensioneY = orto.getDimensioneY();

        for (int x = 1; x <= dimensioneX; x++) {
            for (int y = 1; y <= dimensioneY; y++) {
                Zolla zolla = new Zolla("Zolla_" + x + "_" + y, "sabbioso", null, null, null, null, null, null, orto);
                zollaRepository.save(zolla);
            }
        }
    }

}
