package com.univaq.TestAgile.controller.api;

import com.univaq.TestAgile.model.Evento;
import com.univaq.TestAgile.model.Partecipazione;
import com.univaq.TestAgile.model.Utente;
import com.univaq.TestAgile.repository.EventoRepository;
import com.univaq.TestAgile.repository.PartecipazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/partecipazione")
public class PartecipazioneController {

    @Autowired
    PartecipazioneRepository partecipazioneRepository;

    @Autowired
    EventoRepository eventoRepository;

    @Autowired
    UtenteController utenteController;
    @GetMapping("/addPartecipazione/{id}")
    public String nuovoPartecipazione(@PathVariable long id) {
        Utente utente = utenteController.getUtenteLoggato();

        Evento evento = eventoRepository.findById(id).get();
        Partecipazione nuovaPartecipazione = new Partecipazione(utente,evento);
        partecipazioneRepository.save(nuovaPartecipazione);
        return "redirect:/dashboard";
    }

}
