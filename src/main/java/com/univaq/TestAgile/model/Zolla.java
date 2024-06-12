package com.univaq.TestAgile.model;

import jakarta.persistence.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@EnableJpaRepositories
@Entity
@Table(name = "zolla")
public class Zolla {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "orto_id")
    private OrtoReferente orto;

    @ManyToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;

    @Column(name = "nome")
    private String nome;

    @Column(name = "tipo_terreno")
    private String tipoTerreno;

    @Column(name = "ortaggio")
    private String ortaggio;

    @Column(name = "semina")
    private Date semina;

    @Column(name = "stato")
    private String stato;

    @Column(name = "data_scadenza")
    private Date dataScadenza;

    @Column(name = "data_prenotazione")
    private Date dataPrenotazione;

    @Column(name = "proprietario")
    private String proprietario;

    public Zolla(String nome, String tipoTerreno, String ortaggio, Date semina, String stato, Date dataScadenza, Date dataPrenotazione, String proprietario, OrtoReferente orto) {
        this.nome = nome;
        this.tipoTerreno = tipoTerreno;
        this.ortaggio = ortaggio;
        this.semina = semina;
        this.stato = stato;
        this.dataScadenza = dataScadenza;
        this.dataPrenotazione = dataPrenotazione;
        this.proprietario = proprietario;
        this.orto = orto;
    }

    public Zolla() {
    }

    public String getGiorniPossesso(){
        Date dataPrenotazioneDate = getDataPrenotazione();

        // Convertire la data di prenotazione in LocalDate
        Instant instant = dataPrenotazioneDate.toInstant();
        LocalDate dataPrenotazione = instant.atZone(ZoneId.systemDefault()).toLocalDate();

        // Ottenere la data odierna
        LocalDate dataOdierna = LocalDate.now();

        // Calcolare la differenza di giorni
        long giorniPossesso = ChronoUnit.DAYS.between(dataPrenotazione, dataOdierna);

        // Convertire il risultato in una stringa e restituirlo
        return String.valueOf(giorniPossesso);
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipoTerreno() {
        return tipoTerreno;
    }

    public void setTipoTerreno(String tipoTerreno) {
        this.tipoTerreno = tipoTerreno;
    }

    public String getOrtaggio() {
        return ortaggio;
    }

    public void setOrtaggio(String ortaggio) {
        this.ortaggio = ortaggio;
    }

    public Date getSemina() {
        return semina;
    }

    public void setSemina(Date semina) {
        this.semina = semina;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public Date getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(Date dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    public Date getDataPrenotazione() {
        return dataPrenotazione;
    }

    public void setDataPrenotazione(Date dataPrenotazione) {
        this.dataPrenotazione = dataPrenotazione;
    }

    public String getProprietario() {
        return proprietario;
    }

    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }
}
