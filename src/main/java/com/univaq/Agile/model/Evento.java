package com.univaq.Agile.model;

import jakarta.persistence.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.LocalDateTime;
import java.util.List;

@EnableJpaRepositories
@Entity
@Table(name = "evento")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_evento")
    private String nomeEvento;

    @Column(name = "data_ora_evento")
    private LocalDateTime dataOraEvento;

    @Column(name = "luogo")
    private String luogo;

    @Column(name = "descrizione")
    private String descrizione;

    @Column(name = "data_ora_Creazione")
    private LocalDateTime dataOraCreazione;

    @Column(name = "link_img")
    private String link_img;

    @Column(name = "prezzo")
    private String prezzo;

    @Column(name = "durata")
    private String durataEvento;

    @Column(name = "tema")
    private String temaEvento;


    // Passato, futuro, presente
    @Column(name = "stato_evento_tempo")
    private String statoEvento;

    //IN sospeso rifiutato e accettato
    @Column(name = "accettazione_add")
    private String accettato;

// dati del referente
    @Column(name = "referente")
    private String nomeReferente;

    @Column(name = "id_Referente")
    private Long idReferente;

    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Partecipazione> partecipazioni;

    public Evento(String nome, String referente, Long idReferente, LocalDateTime dataOraEvento, String luogo, String descrizione, LocalDateTime dataOraCreazione, String link_img, String prezzo, String durataEvento, String temaEvento, String statoEvento, String accettato) {
        this.nomeEvento = nome;
        this.nomeReferente = referente;
        this.idReferente = idReferente;
        this.dataOraEvento = dataOraEvento;
        this.luogo = luogo;
        this.descrizione = descrizione;
        this.dataOraCreazione = dataOraCreazione;
        this.link_img = link_img;
        this.prezzo = prezzo;
        this.durataEvento = durataEvento;
        this.temaEvento = temaEvento;
        this.statoEvento = statoEvento;
        this.accettato = accettato;

    }

    public Evento() {

    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

    public String getNomeReferente() {
        return nomeReferente;
    }

    public void setNomeReferente(String nomeReferente) {this.nomeReferente = nomeReferente;
    }

    public LocalDateTime getDataOraEvento() {
        return dataOraEvento;
    }

    public void setDataOraEvento(LocalDateTime dataOraEvento) {
        this.dataOraEvento = dataOraEvento;
    }

    public String getLuogo() {
        return luogo;
    }

    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public LocalDateTime getDataOraCreazione() {
        return dataOraCreazione;
    }

    public void setDataOraCreazione(LocalDateTime dataOraCreazione) {
        this.dataOraCreazione = dataOraCreazione;
    }

    public String getLink_img() {
        return link_img;
    }

    public void setLink_img(String link_img) {
        this.link_img = link_img;
    }

    public String getStatoEvento() {
        return statoEvento;
    }

    public void setStatoEvento(String statoEvento) {
        this.statoEvento = statoEvento;
    }

    public Long getId() {
        return id;
    }

    public Long getId2() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdReferente() {
        return idReferente;
    }
    public void setIdReferente(Long idRef) {
        this.idReferente = idRef;
    }

    public String getAccettato() {
        return accettato;
    }

    public void setAccettato(String accettato) {
        this.accettato = accettato;
    }

    public String getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(String prezzo) {
        this.prezzo = prezzo;
    }

    public String getDurataEvento() {
        return durataEvento;
    }

    public void setDurataEvento(String durataEvento) {
        this.durataEvento = durataEvento;
    }

    public String getTemaEvento() {
        return temaEvento;
    }

    public void setTemaEvento(String temaEvento) {
        this.temaEvento = temaEvento;
    }

    public List<Partecipazione> getPartecipazioni() {
        return partecipazioni;
    }

    public void setPartecipazioni(List<Partecipazione> partecipazioni) {
        this.partecipazioni = partecipazioni;
    }
}
