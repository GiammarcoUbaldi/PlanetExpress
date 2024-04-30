package com.univaq.TestAgile.controller;

import com.univaq.TestAgile.model.Cliente;
import com.univaq.TestAgile.model.Evento;
import com.univaq.TestAgile.repository.ClienteRepository;
import com.univaq.TestAgile.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/evento")
public class EventoController {

    @Autowired
    private EventoRepository eventoRepository;

    @GetMapping("/giovanni")
    public List<Evento> getAllEventi() {
        return (List<Evento>) eventoRepository.findAll();
    }

    @PostMapping
    public Evento createEvento(@RequestBody Evento evento) {
        return eventoRepository.save(evento);
    }

}
