package com.univaq.TestAgile.controller.api;

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
                1L,
                LocalDateTime.of(2024, 05, 15, 10, 00),
                "Orto Comunale - Via Roma",
                "Preparazione del terreno per la semina e il trapianto",
                LocalDateTime.now(),
                "/img/evento (1).jpg",
                "20$",
                "2:00 ore",
                "Agricoltura",
                "Organizzato",
                              
                "Rifiutato"));
        eventi.add(new Evento("Raccolta della vita",
                "Marta Verdi",
                2L,
                LocalDateTime.of(2024, 06, 01, 16, 00),
                "Orto Comunale - Via Roma",
                "Semina di pomodori, lattuga, carote e altre verdure",
                LocalDateTime.now(),
                "/img/evento (2).jpg",
                "30$",
                "1 giorno",
                "Vegetali",
                "In Corso",
                "In sospeso"));
        eventi.add(new Evento("Semina ortaggi primaverili",
                "Marta Verdi",
                2L,
                LocalDateTime.of(2024, 10, 01, 16, 00),
                "Campagna di Giovanni",
                "Semina di pomodori, lattuga, carote e altre verdure",
                LocalDateTime.now(),
                "/img/evento (3).jpg",
                "40$",
                "4 ore",
                "Storie",
                "In Corso",
                "In sospeso"));
        eventi.add(new Evento("Semina ortaggi primaverili",
                "Marta Verdi",
                2L,
                LocalDateTime.of(2024, 10, 01, 16, 00),
                "italia",
                "Semina di pomodori, lattuga, carote e altre verdure",
                LocalDateTime.now(),
                "/img/evento (4).jpg",
                "40$",
                "4 ore",
                "Storie",
                "In Corso",
                "In sospeso"));
        eventi.add(new Evento("Semina di vegetali",
                "Marta Verdi",
                2L,
                LocalDateTime.of(2024, 10, 01, 16, 00),
                "Via dell'autunno 23 Roma",
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis ",
                LocalDateTime.now(),
                "/img/evento (5).jpg",
                "40$",
                "4 ore",
                "Storie",
                "In Corso",
                "In sospeso"));
        eventi.add(new Evento("Caricatura Paglia",
                "Marta Verdi",
                2L,
                LocalDateTime.of(2022, 10, 01, 16, 00),
                "Campi autunnali del Sospiro",
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis ",
                LocalDateTime.now(),
                "/img/evento (6).jpg",
                "Gratuito",
                "4 ore",
                "Vita e Gioventù",
                "In Corso",
                "In sospeso"));
        eventi.add(new Evento("Vita in collina",
                "Marta Verdi",
                2L,
                LocalDateTime.of(2022, 11, 01, 16, 00),
                "Mare e montagna",
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis ",
                LocalDateTime.now(),
                "/img/evento (1).jpg",
                "Gratuito",
                "1 ora",
                "Vita",
                "In Corso",
                "In sospeso"));
        eventi.add(new Evento("Corso di irrigazione efficiente",
                "Esperto Irrigazione",
                3L,
                LocalDateTime.of(2024, 07, 10, 18, 00),
                "Sala Conferenze - Comune di L'Aquila",
                "Tecniche per un'irrigazione efficiente e sostenibile",
                LocalDateTime.now(),
                "/img/evento (2).jpg",
                "5$",
                "3:00 ore",
                "Acqua",
                "Passato",
                "Accettato"));
        eventi.add(new Evento("Festa di inaugurazione orto",
                "Gruppo Ortolani",
                4L,
                LocalDateTime.of(2024, 05, 25, 18, 00),
                "Orto Comunale - Via Roma",
                "Festa per celebrare l'apertura dell'orto e conoscere i nuovi partecipanti",
                LocalDateTime.now(),
                "/img/evento (3).jpg",
                "Gratuito",
                "5:00 ore",
                "Inaugurazione",
                "Prossimo",
                "Accettato"));
        eventi.add(new Evento(
                "Condivisione semi e piantine",
                "Marta Verdi",
                2L,
                LocalDateTime.of(2024, 05, 25, 18, 00),
                "Orto Comunale - Via Roma",
                "Porta i tuoi semi e piantine in eccedenza e scambiali con gli altri",
                LocalDateTime.now(),
                "/img/evento (4).jpg",
                "20$",
                "2:00 ore",
                "Orto",
                "Passato",
                "In sospeso"));
        eventi.add(new Evento("Laboratorio di compostaggio",
                "Esperto di Compostaggio",
                5L,
                LocalDateTime.of(2024, 07, 17, 15, 00),
                "Sala Conferenze - Comune di L'Aquila",
                "Impara a creare il tuo compost per nutrire il tuo orto in modo naturale",
                LocalDateTime.now(),
                "/img/evento (5).jpg",
                "Gratuito",
                "2:00 ore",
                "Cibo",
                "Aperto",
                "Rifiutato"));
        eventoRepository.deleteAll();
        eventoRepository.saveAll(eventi);


    }

    public void inserisciDatiOrtoReferente() {
        List<OrtoReferente> eventi = new ArrayList<OrtoReferente>();
//        eventi.add(new OrtoReferente(
//                "Orto 'Il Giardino degli Amici'",
//                "Via Roma, 123 - L'Aquila",
//                "Spazioso orto con terreno fertile e soleggiato. Ideale per coltivare ortaggi e verdure di stagione.",
//                50,
//                30,
//                400,
//                "Vicino a fermata autobus e parcheggio",
//                "Mario Rossi",
//                "rifiutato"));
//        eventi.add(new OrtoReferente(
//                "Orto 'Erbe aromatiche e spezie'",
//                "Cortile interno - Condominio 'Le Palme'",
//                "Piccolo orto dedicato alle erbe aromatiche e alle spezie. Perfetto per insaporire i tuoi piatti.",
//                20,
//                15,
//                100,
//                "Accesso su richiesta",
//                "Anna Bianchi",
//                "accettato"));
//        eventi.add(new OrtoReferente(
//                "Orto 'Condividiamo la Natura'",
//                "Zona periferica - L'Aquila",
//                "Ampio orto immerso nel verde. Ideale per coltivare in gruppo e condividere i prodotti.",
//                100,
//                80,
//                1200,
//                "Attrezzi e compost a disposizione",
//                "Team Ortolani Volontari",
//                "accettato"));
//        eventi.add(new OrtoReferente("Orto 'Il Giardino degli Amici'",
//                "Via Roma, 123 - L'Aquila",
//                "Spazioso orto con terreno fertile e soleggiato. Ideale per coltivare ortaggi e verdure di stagione.",
//                50,
//                30,
//                400,
//                "Vicino a fermata autobus e parcheggio",
//                "Mario Rossi",
//                "indefinito"));
//        eventi.add(new OrtoReferente("Orto 'Erbe aromatiche e spezie'",
//                "Cortile interno - Condominio 'Le Palme'",
//                "Piccolo orto dedicato alle erbe aromatiche e alle spezie. Perfetto per insaporire i tuoi piatti.",
//                20,
//                15,
//                100,
//                "Accesso su richiesta",
//                "Anna Bianchi",
//                "indefinito"));
//        eventi.add(new OrtoReferente("Orto 'Condividiamo la Natura'",
//                "Zona periferica - L'Aquila",
//                "Ampio orto immerso nel verde. Ideale per coltivare in gruppo e condividere i prodotti.",
//                100,
//                80,
//                1200,
//                "Attrezzi e compost a disposizione",
//                "Team Ortolani Volontari",
//                "indefinito"));
        ortoReferenteRepository.deleteAll();
        ortoReferenteRepository.saveAll(eventi);
    }

    public void inserisciDati() {
        inserisciDatiEvento();
     //   inserisciDatiOrtoReferente();
    //   inserisciDatiUtente();
    }

    @Autowired
    private UtenteRepository utenteRepository;
    private void inserisciDatiUtente() {
        List<Utente> eventi = new ArrayList<Utente>();
        eventi.add(new Utente("Giovanni","Altieri","giovanni.alt1@gmail.com","$2a$10$QS7ZDUF1wg2NAFKWs9PrUedrFvzKa.otRCAgRPehLfV7pdv7xl2/m","USER","maschio","lentella","italia","333232323"));
        eventi.add(new Utente("Luca","Di Cicco","ref@ref.com","$2a$10$QS7ZDUF1wg2NAFKWs9PrUedrFvzKa.otRCAgRPehLfV7pdv7xl2/m","REFERENTE","maschio","roma","italia","343232323"));
        eventi.add(new Utente("Mario","Rossi","admin@admin.com","$2a$10$QS7ZDUF1wg2NAFKWs9PrUedrFvzKa.otRCAgRPehLfV7pdv7xl2/m","ADMIN","femmina","vasto","italia","333233423"));

        utenteRepository.deleteAll();
        utenteRepository.saveAll(eventi);

    }
}
