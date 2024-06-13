package com.univaq.TestAgile.controller.utente;


import com.univaq.TestAgile.controller.api.EventoController;
import com.univaq.TestAgile.controller.api.OrtoReferenteController;
import com.univaq.TestAgile.controller.api.UtenteController;
import com.univaq.TestAgile.controller.api.ZollaController;
import com.univaq.TestAgile.model.Evento;
import com.univaq.TestAgile.model.OrtoReferente;

import com.univaq.TestAgile.model.Utente;
import com.univaq.TestAgile.model.Zolla;
import com.univaq.TestAgile.repository.PartecipazioneRepository;
import com.univaq.TestAgile.repository.UtenteRepository;
import com.univaq.TestAgile.repository.ZollaRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private PartecipazioneRepository partecipazioneRepository;

    @Autowired
    UtenteController utenteController;

    @Autowired
    EventoController eventoController;

    @Autowired
    UtenteRepository utenteRepository;


    @Autowired
    OrtoReferenteController ortoReferenteController;

    @Autowired
    ZollaController zollaController;

    @Autowired
    private ZollaRepository zollaRepository;

    @GetMapping("/dashboard")
    public String dashboard() {
        return "utente/dashboardUtente";
    }


    @GetMapping("/mostraEventiUser")
    public String mostraEventiUtente(Model model) {
        Utente user = utenteController.getUtenteLoggato();
        List<Evento> eventiTotali = eventoController.getEventi3User();
        model.addAttribute("EventiTotali", eventiTotali);
        List<Evento> eventiCitta = eventoController.getEventiCitta(user.getNazione());
        model.addAttribute("EventiCitta", eventiCitta);
        List<Evento> eventiPrenotati = eventoController.getEventiPrenotati(user);
        model.addAttribute("EventiPrenotati", eventiPrenotati);
        return "utente/listaEventiUser";
    }

    @GetMapping("/mostraDettagliEvento/{id}")
    public String mostraDettagliEvento(Model model, @PathVariable long id) {
        Evento evento = eventoController.getEventoById(id);
        Utente utente = utenteController.getUtenteLoggato();
        boolean utentePrenotato = partecipazioneRepository.existsByEventoIdAndUtenteId(id, utente.getId());
        model.addAttribute("datiDettagli", evento);
        model.addAttribute("utentePrenotato", utentePrenotato);
        return "utente/dettagliEventoUser";
    }


    @GetMapping("/listaOrtiDisponibili")
    public String listaOrtiDisponibili(Model model) {
        List<OrtoReferente> ortiDisponibili = ortoReferenteController.getRichiesteOrtiAccettate();
        model.addAttribute("ortiDisponibili", ortiDisponibili);
        return "utente/listaOrtiDisponibili";
    }

    @GetMapping("/dashboardModifica")
    public String utenteDashboardModifica(Model model, @RequestParam("utenteId") Long utenteId) {
        Utente utente = utenteRepository.findById(utenteId).get();
        System.out.println(utente);
        if (utente != null) {
            model.addAttribute("utente", utente);
            return "/autenticazione/modificaDati";
        }
        return "redirect:/";
    }


    @GetMapping("/occupazioneOrto")
    public String utenteRichiestaOccupazioneOrto(Model model, @RequestParam("ortoId") Long ortoId) {
        Utente utente = utenteController.getUtenteLoggato();
        OrtoReferente ortoOccupato = ortoReferenteController.getOrtoById(ortoId);
        utente.setOrtoOccupato(ortoOccupato);

        List<Zolla> listaTutteZolle = zollaController.getListZolleByIdOrto(ortoId);
        // Filtra la lista per rimuovere gli elementi con ortaggio non nullo
        List<Zolla> listaZolleFiltrata = listaTutteZolle.stream()
                .filter(zolla -> zolla.getUtente() == null)
                .collect(Collectors.toList());

        // Verifica che la lista filtrata non sia vuota
        if (listaZolleFiltrata.isEmpty()) {
            return null; // O lancia un'eccezione a seconda del tuo caso d'uso
        }

        // Seleziona un elemento casuale dalla lista filtrata
        Random random = new Random();
        int index = random.nextInt(listaZolleFiltrata.size());
        Zolla zollaPrenotata = listaZolleFiltrata.get(index);

        zollaPrenotata.setUtente(utente);
        zollaPrenotata.setDataPrenotazione(new Date());
        zollaRepository.save(zollaPrenotata);


        utenteRepository.save(utente);
        return "redirect:/dashboard";
    }

    @GetMapping("/ortoOccupato")
    public String utenteOrtoOccupato(Model model) {
        Utente utente = utenteController.getUtenteLoggato();
        if (utente == null) return "redirect:/dashboard";

        OrtoReferente ortoOccupato = utente.getOrtoOccupato();
        OrtoReferente ortoReferente = ortoReferenteController.getOrtoById(ortoOccupato.getId());
        List<Zolla> zolle = zollaController.getListZolleByOrto(ortoReferente);
        List<Zolla> zolleDelUtente = zollaController.getListZolleByOrtoAndUtente(ortoReferente, utente);
        model.addAttribute("ortoReferente", ortoReferente);
        //  model.addAttribute("zolle", zolle);
        model.addAttribute("zolleDelUtente", zolleDelUtente);
        return "/utente/orto/visualizzaOrtoUtente";
    }


    @GetMapping("/richiestaZollaAggiuntiva")
    public String richiestaZollaAggiuntiva(Model model) {
        Utente utente = utenteController.getUtenteLoggato();
        if (utente == null) return "redirect:/dashboard";

        OrtoReferente ortoOccupato = utente.getOrtoOccupato();
        List<Zolla> listaTutteZolle = zollaController.getListZolleByIdOrto(ortoOccupato.getId());
        // Filtra la lista per rimuovere gli elementi con ortaggio non nullo
        List<Zolla> listaZolleFiltrata = listaTutteZolle.stream()
                .filter(zolla -> zolla.getUtente() == null)
                .collect(Collectors.toList());

        // Verifica che la lista filtrata non sia vuota
        if (listaZolleFiltrata.isEmpty()) {
            return "redirect:/user/ortoOccupato";// O lancia un'eccezione a seconda del tuo caso d'uso
        }

        // Seleziona un elemento casuale dalla lista filtrata
        Random random = new Random();
        int index = random.nextInt(listaZolleFiltrata.size());
        Zolla zollaPrenotata = listaZolleFiltrata.get(index);


        zollaPrenotata.setUtente(utente);
        zollaPrenotata.setDataPrenotazione(new Date());
        zollaRepository.save(zollaPrenotata);

        return "redirect:/user/ortoOccupato";
    }


}
