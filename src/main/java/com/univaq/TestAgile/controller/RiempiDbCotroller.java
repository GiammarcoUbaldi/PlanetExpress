package com.univaq.TestAgile.controller;

import com.univaq.TestAgile.model.Evento;
import com.univaq.TestAgile.model.OrtoReferente;
import com.univaq.TestAgile.repository.EventoRepository;
import com.univaq.TestAgile.repository.OrtoReferenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.univaq.TestAgile.model.Evento;
import com.univaq.TestAgile.model.OrtoReferente;
import com.univaq.TestAgile.model.Utente;
import com.univaq.TestAgile.repository.EventoRepository;
import com.univaq.TestAgile.repository.OrtoReferenteRepository;
import com.univaq.TestAgile.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/riempiDb")
public class RiempiDbCotroller {

    @Autowired
    private EventoRepository eventoRepository;
    @Autowired
    private OrtoReferenteRepository ortoReferenteRepository;

    public void inserisciDatiEvento() {

        //Inserimento degli eventi
        List<Evento> eventi = new ArrayList<Evento>();
        eventi.add(new Evento("Preparazione terreno orto comune",
                "Gruppo Ortolani",
                LocalDateTime.of(2024, 05, 15, 10, 00),
                "Orto Comunale - Via Roma",
                "Preparazione del terreno per la semina e il trapianto",
                LocalDateTime.now(),
                "https://source.unsplash.com/random/300x300?seed=1234345",
                "Organizzato",
                "Rifiutato"));
        eventi.add(new Evento("Semina ortaggi primaverili",
                "Marta Verdi",
                LocalDateTime.of(2024, 06, 01, 16, 00),
                "Orto Comunale - Via Roma",
                "Semina di pomodori, lattuga, carote e altre verdure",
                LocalDateTime.now(),
                "https://source.unsplash.com/random/300x300?seed=2312345",
                "In Corso",
                "In sospeso"));
        eventi.add(new Evento("Corso di irrigazione efficiente",
                "Esperto Irrigazione",
                LocalDateTime.of(2024, 07, 10, 18, 00),
                "Sala Conferenze - Comune di L'Aquila",
                "Tecniche per un'irrigazione efficiente e sostenibile",
                LocalDateTime.now(),
                "https://source.unsplash.com/random/300x300?seed=1234435",
                "Passato",
                "Accettato"));
        eventi.add(new Evento("Festa di inaugurazione orto",
                "Gruppo Ortolani",
                LocalDateTime.of(2024, 05, 25, 18, 00),
                "Orto Comunale - Via Roma",
                "Festa per celebrare l'apertura dell'orto e conoscere i nuovi partecipanti",
                LocalDateTime.now(),
                "https://source.unsplash.com/random/300x300?seed=1232345",
                "Prossimo",
                "Accettato"));
        eventi.add(new Evento(
                "Condivisione semi e piantine",
                "Marta Verdi",
                LocalDateTime.of(2024, 05, 25, 18, 00),
                "Orto Comunale - Via Roma",
                "Porta i tuoi semi e piantine in eccedenza e scambiali con gli altri",
                LocalDateTime.now(),
                "https://source.unsplash.com/random/300x300?seed=134245",
                "Passato",
                "In sospeso"));
        eventi.add(new Evento("Laboratorio di compostaggio",
                "Esperto di Compostaggio",
                LocalDateTime.of(2024, 07, 17, 15, 00),
                "Sala Conferenze - Comune di L'Aquila",
                "Impara a creare il tuo compost per nutrire il tuo orto in modo naturale",
                LocalDateTime.now(),
                "https://source.unsplash.com/random/300x300?seed=12345",
                "Aperto",
                "Rifiutato"));
        eventoRepository.deleteAll();
        eventoRepository.saveAll(eventi);


    }

    public void inserisciDatiOrtoReferente() {
        List<OrtoReferente> eventi = new ArrayList<OrtoReferente>();
        eventi.add(new OrtoReferente(
                "Orto 'Il Giardino degli Amici'",
                "Via Roma, 123 - L'Aquila",
                "Spazioso orto con terreno fertile e soleggiato. Ideale per coltivare ortaggi e verdure di stagione.",
                50,
                30,
                400,
                "Vicino a fermata autobus e parcheggio",
                "Mario Rossi",
                "rifiutato"));
        eventi.add(new OrtoReferente(
                "Orto 'Erbe aromatiche e spezie'",
                "Cortile interno - Condominio 'Le Palme'",
                "Piccolo orto dedicato alle erbe aromatiche e alle spezie. Perfetto per insaporire i tuoi piatti.",
                20,
                15,
                100,
                "Accesso su richiesta",
                "Anna Bianchi",
                "accettato"));
        eventi.add(new OrtoReferente(
                "Orto 'Condividiamo la Natura'",
                "Zona periferica - L'Aquila",
                "Ampio orto immerso nel verde. Ideale per coltivare in gruppo e condividere i prodotti.",
                100,
                80,
                1200,
                "Attrezzi e compost a disposizione",
                "Team Ortolani Volontari",
                "accettato"));
        eventi.add(new OrtoReferente("Orto 'Il Giardino degli Amici'",
                "Via Roma, 123 - L'Aquila",
                "Spazioso orto con terreno fertile e soleggiato. Ideale per coltivare ortaggi e verdure di stagione.",
                50,
                30,
                400,
                "Vicino a fermata autobus e parcheggio",
                "Mario Rossi",
                "indefinito"));
        eventi.add(new OrtoReferente("Orto 'Erbe aromatiche e spezie'",
                "Cortile interno - Condominio 'Le Palme'",
                "Piccolo orto dedicato alle erbe aromatiche e alle spezie. Perfetto per insaporire i tuoi piatti.",
                20,
                15,
                100,
                "Accesso su richiesta",
                "Anna Bianchi",
                "indefinito"));
        eventi.add(new OrtoReferente("Orto 'Condividiamo la Natura'",
                "Zona periferica - L'Aquila",
                "Ampio orto immerso nel verde. Ideale per coltivare in gruppo e condividere i prodotti.",
                100,
                80,
                1200,
                "Attrezzi e compost a disposizione",
                "Team Ortolani Volontari",
                "indefinito"));
        ortoReferenteRepository.deleteAll();
        ortoReferenteRepository.saveAll(eventi);
    }

    public void inserisciDati() {
        inserisciDatiEvento();
        inserisciDatiOrtoReferente();
        inserisciDatiUtente();
    }

    @Autowired
    private UtenteRepository utenteRepository;
    private void inserisciDatiUtente() {
        List<Utente> eventi = new ArrayList<Utente>();
        eventi.add(new Utente("utente", "password", "utente"));
        eventi.add(new Utente("admin", "password", "admin"));
        eventi.add(new Utente("referent", "password", "referente"));
        utenteRepository.deleteAll();
        utenteRepository.saveAll(eventi);
    }
}
