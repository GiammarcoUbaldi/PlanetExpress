package com.univaq.TestAgile.controller.api;

import com.univaq.TestAgile.model.Evento;
import com.univaq.TestAgile.model.OrtoReferente;
import com.univaq.TestAgile.model.Partecipazione;
import com.univaq.TestAgile.model.Utente;
import com.univaq.TestAgile.repository.EventoRepository;
import com.univaq.TestAgile.repository.PartecipazioneRepository;
import com.univaq.TestAgile.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/evento")
public class EventoController {

    @Autowired
    private UtenteController utenteController;

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private PartecipazioneRepository partecipazioneRepository;

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

//-------------------------------------- REFERENTE
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

    @GetMapping("/refInSospeso")
    public List<Evento> getEventiInSospesoRef() {
        return (List<Evento>) eventoRepository.findByAccettato("In sospeso");
    }

    //-------------------------------------- USER

    @GetMapping("/eventiCitta")
    public List<Evento> getEventiCitta(String citta) {
        List<Evento> listaEventiCitta =  eventoRepository.findByLuogo(citta);
        List<Evento> listaEventiFiltrati = new ArrayList<>();

        for(Evento evento : listaEventiCitta){
            if(evento.getLuogo().equals(citta)) listaEventiFiltrati.add(evento);
        }
        return listaEventiFiltrati;
    }


    public List<Evento> getEventi3User() {
        Utente utente = utenteController.getUtenteLoggato();
        List<Evento> eventi = eventoRepository.findByAccettato("Accettato");
        List<Evento> eventiPrenotati = getEventiPrenotati(utente);


        List<Evento> eventiNonPrenotati = new ArrayList<>();

        for (Evento evento : eventi) {
            boolean isPrenotato = false;
            for (Evento eventoPrenotato : eventiPrenotati) {
                if (eventoPrenotato.getNomeEvento().equals(evento.getNomeEvento())) {
                    isPrenotato = true;
                    break;
                }
            }
            if (!isPrenotato) {
                eventiNonPrenotati.add(evento);
            }
        }

        if (eventiNonPrenotati.size() > 3) {
            eventiNonPrenotati = eventiNonPrenotati.subList(0, 3);
        }

        return eventiNonPrenotati;
    }


    @GetMapping("/eventiPrenotati")
    public List<Evento> getEventiPrenotati(Utente utente) {
        List<Partecipazione> prenotazioni = partecipazioneRepository.findByUtente(utente);
        List<Evento> eventiPrenotati = new ArrayList<>();
        for (Partecipazione partecipazione : prenotazioni){
            eventiPrenotati.add(partecipazione.getEvento());
        }

        return eventiPrenotati;

    }

    @GetMapping("/isPrenotato/{eventoId}")
    public boolean isUtentePrenotato(@PathVariable Long eventoId) {
        Utente utente = utenteController.getUtenteLoggato(); // Supponendo che tu abbia un metodo per ottenere l'utente loggato
        return partecipazioneRepository.existsByEventoIdAndUtenteId(eventoId, utente.getId());
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

    @GetMapping("/eventiRef33")
    public List<Evento> getEventi33() {
        List<Evento> eventi = eventoRepository.findByAccettato("Accettato");

        if (eventi.size() > 3) {
            eventi = eventi.subList(0, 3);
        }

        return eventi;
    }

    @PostMapping("/add-richiesta-evento")
    public String nuovoEvento(@ModelAttribute Evento eventoNuovo) {
        eventoRepository.save(eventoNuovo);
        return "redirect:/";
    }

}

