package com.univaq.Agile.controller.api;

import com.univaq.Agile.model.Evento;
import com.univaq.Agile.model.Partecipazione;
import com.univaq.Agile.model.Utente;
import com.univaq.Agile.repository.EventoRepository;
import com.univaq.Agile.repository.PartecipazioneRepository;
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
