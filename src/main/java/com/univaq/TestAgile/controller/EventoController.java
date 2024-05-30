package com.univaq.TestAgile.controller;

import com.univaq.TestAgile.model.Evento;
import com.univaq.TestAgile.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/evento")
public class EventoController {

    @Autowired
    private EventoRepository eventoRepository;

    @GetMapping("/accettati")
    public List<Evento> getEventiAccettati() {
        return (List<Evento>) eventoRepository.findByAccettato("Accettato");
    }

    @GetMapping("/rifiutati")
    public List<Evento> getEventiRifiutati() {
        return (List<Evento>) eventoRepository.findByAccettato("Rifiutato");
    }

    @GetMapping("/in_sospeso")
    public List<Evento> getEventiInSospeso() {
        return (List<Evento>) eventoRepository.findByAccettato("In sospeso");
    }

//--------------------------------------
    @GetMapping("/eventiRef")
    public List<Evento> getEventiProposti(Long id) {
        return (List<Evento>) eventoRepository.findByIdReferente(id);
    }


    @GetMapping("/inSospeso_da_admin")
    public List<Evento> getEventiAccettatiRef(Long id) {
         List<Evento> listaEventi =  eventoRepository.findByAccettato("In sospeso");
         List<Evento> listaEventiFiltrati = new ArrayList<>();

         for(Evento evento : listaEventi){
             if(evento.getIdReferente().equals(id)) listaEventiFiltrati.add(evento);
         }
         return listaEventiFiltrati;
    }

    @GetMapping("/futuri")
    public List<Evento> getEventiFuturiRef(Long id) {
        List<Evento> listaEventi =  eventoRepository.findByAccettato("Accettato");
        List<Evento> listaEventiFiltrati = new ArrayList<>();

        for(Evento evento : listaEventi){
            LocalDateTime now = LocalDateTime.now();
                if (evento.getIdReferente().equals(id) && evento.getDataOraEvento().isAfter(LocalDateTime.now())) {
                    listaEventiFiltrati.add(evento);
                }
        }

        return listaEventiFiltrati;
    }




    @PostMapping
    public Evento createEvento(@RequestBody Evento evento) {
        return eventoRepository.save(evento);
    }

    @GetMapping("/aggiornamento/{id}/{valoreNuovo}")
    public String updateStato(@PathVariable long id, @PathVariable String valoreNuovo) {
        Evento evento = eventoRepository.findById(id).get();
        evento.setAccettato(valoreNuovo);
        eventoRepository.save(evento);

        return "redirect:/mostraEventi";
    }

    @GetMapping("/get/{id}/")
    public Evento getEventoById(@PathVariable long id) {
        return eventoRepository.findById(id).get();
    }

    @GetMapping("/getEveRef/{id}/")
    public List<Evento> getEventiByIdRef(@PathVariable long id) {
        return eventoRepository.findByIdReferente(id);
    }

    @GetMapping("/eventiRef3")
    public List<Evento> getEventi3(Long id) {
        List<Evento> eventi = eventoRepository.findByIdReferente(id); //che gli passo?

        if (eventi.size() > 3) {
            eventi = eventi.subList(0, 3);
        }

        return eventi;
    }

    @GetMapping("/refInSospeso")
    public List<Evento> getEventiInSospesoRef() {
        return (List<Evento>) eventoRepository.findByAccettato("In sospeso");
    }

}

