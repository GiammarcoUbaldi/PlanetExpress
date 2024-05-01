package com.univaq.TestAgile.model;

import jakarta.persistence.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

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

    @Column(name = "stato_evento")
    private String statoEvento;

    @Column(name = "accettazione")
    private String accettato;
    /*
    @Column(name = "admin_responsabile")
    private String adminResponsabile;
*/

        @Column(name = "referente")
    private String nomeReferente;

    public Evento(String nome, String referente, LocalDateTime dataOraEvento, String luogo, String descrizione, LocalDateTime dataOraCreazione, String link_img, String statoEvento, String accettato) {
        this.nomeEvento = nome;
        this.nomeReferente = referente;
        this.dataOraEvento = dataOraEvento;
        this.luogo = luogo;
        this.descrizione = descrizione;
        this.dataOraCreazione = dataOraCreazione;
        this.link_img = link_img;
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

    public void setNomeReferente(String nomeReferente) {
        this.nomeEvento = nomeReferente;
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

    public String getAccettato() {
        return accettato;
    }

    public void setAccettato(String accettato) {
        this.accettato = accettato;
    }
}
