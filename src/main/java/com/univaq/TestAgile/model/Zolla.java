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

    @Column(name = "raccolta")
    private Date raccolta;

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

    public Zolla(OrtoReferente orto, Utente utente, String nome, String tipoTerreno, String ortaggio, Date semina, Date raccolta, String stato, Date dataScadenza, Date dataPrenotazione, String proprietario) {
        this.orto = orto;
        this.utente = utente;
        this.nome = nome;
        this.tipoTerreno = tipoTerreno;
        this.ortaggio = ortaggio;
        this.semina = semina;
        this.raccolta = raccolta;
        this.stato = stato;
        this.dataScadenza = dataScadenza;
        this.dataPrenotazione = dataPrenotazione;
        this.proprietario = proprietario;
    }

    public OrtoReferente getOrto() {
        return orto;
    }

    public void setOrto(OrtoReferente orto) {
        this.orto = orto;
    }

    public Date getRaccolta() {
        return raccolta;
    }

    public void setRaccolta(Date raccolta) {
        this.raccolta = raccolta;
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

    public int calcolaPercentualeCrescita() {
        int percentuale = 0;
        System.out.println("Zolla Percent");
        System.out.println(semina);
        System.out.println(raccolta);
        if(semina == null || raccolta == null) return percentuale;
        LocalDate dataSemina = semina.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate dataRaccolta = raccolta.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate dataOdierna = LocalDate.now();

        long giorniTotali = ChronoUnit.DAYS.between(dataSemina, dataRaccolta);
        long giorniTrascorsi = ChronoUnit.DAYS.between(dataSemina, dataOdierna);

        // Evitare divisioni per zero
        if (giorniTotali == 0) {
            return 100;
        }

        // Calcolare la percentuale
         percentuale = (int) ((double) giorniTrascorsi / giorniTotali * 100);

        // Assicurarsi che la percentuale sia tra 0 e 100
        if (percentuale < 0) {
            percentuale = 0;
        } else if (percentuale > 100) {
            percentuale = 100;
        }
        System.out.println(percentuale);
        return percentuale;
    }


    public boolean isDayRaccolta() {
        if (raccolta == null) {
            return false;
        }

        // Converti la data di raccolta a LocalDate
        LocalDate dataRaccolta = raccolta.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        // Ottieni la data odierna
        LocalDate dataOdierna = LocalDate.now();

        // Controlla se la data di raccolta è oggi o nel passato
        return !dataRaccolta.isAfter(dataOdierna);
    }
}

