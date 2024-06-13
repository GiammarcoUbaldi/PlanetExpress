package com.univaq.Agile.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "partecipazioni")
public class Partecipazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;

    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Evento evento;

    @Column(name = "data_partecipazione")
    private LocalDateTime dataPartecipazione;

    public Partecipazione() {
    }

    public Partecipazione (Utente utente, Evento evento) {
        this.utente = utente;
        this.evento = evento;
        this.dataPartecipazione = evento.getDataOraEvento();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public LocalDateTime getDataPartecipazione() {
        return dataPartecipazione;
    }

    public void setDataPartecipazione(LocalDateTime dataPartecipazione) {
        this.dataPartecipazione = dataPartecipazione;
    }
}

