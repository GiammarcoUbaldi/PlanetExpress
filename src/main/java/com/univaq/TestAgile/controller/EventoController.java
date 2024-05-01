package com.univaq.TestAgile.controller;

import com.univaq.TestAgile.model.Cliente;
import com.univaq.TestAgile.model.Evento;
import com.univaq.TestAgile.repository.ClienteRepository;
import com.univaq.TestAgile.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

}
