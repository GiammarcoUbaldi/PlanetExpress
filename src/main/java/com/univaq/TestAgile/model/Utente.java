package com.univaq.TestAgile.model;

import jakarta.persistence.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@EnableJpaRepositories
@Entity
@Table(name = "utenti")
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cognome")
    private String cognome;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "tipo_utente")
    private String tipoUtente;

    @Column(name = "sesso")
    private String sesso;

    @Column(name = "indirizzo")
    private String indirizzo;

    @Column(name = "nazione")
    private String nazione;

    @Column(name = "numero_telefono")
    private String numeroTelefono;

    @ManyToOne
    @JoinColumn(name = "orto_id")
    private OrtoReferente ortoOccupato;

    @OneToMany(mappedBy = "utente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Commento> commenti;

    @OneToMany(mappedBy = "proprietario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Zolla> zolle;

    public Utente() {
    }

    public Utente(String nome, String cognome, String email, String password, String tipoUtente, String sesso, String indirizzo, String nazione, String numeroTelefono) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
        this.tipoUtente = tipoUtente;
        this.sesso = sesso;
        this.indirizzo = indirizzo;
        this.nazione = nazione;
        this.numeroTelefono = numeroTelefono;
        this.ortoOccupato = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSesso() {
        return sesso;
    }

    public void setSesso(String sesso) {
        this.sesso = sesso;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getNazione() {
        return nazione;
    }

    public void setNazione(String nazione) {
        this.nazione = nazione;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public String getTipoUtente() {
        return tipoUtente;
    }

    public void setTipoUtente(String tipoUtente) {
        this.tipoUtente = tipoUtente;
    }

    public OrtoReferente getOrtoOccupato() {
        return ortoOccupato;
    }

    public void setOrtoOccupato(OrtoReferente ortoOccupato) {
        this.ortoOccupato = ortoOccupato;
    }

    public List<Commento> getCommenti() {
        return commenti;
    }

    public void setCommenti(List<Commento> commenti) {
        this.commenti = commenti;
    }

    public List<Zolla> getZolle() {
        return zolle;
    }

    public void setZolle(List<Zolla> zolle) {
        this.zolle = zolle;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", sesso='" + sesso + '\'' +
                ", indirizzo='" + indirizzo + '\'' +
                ", nazione='" + nazione + '\'' +
                ", numeroTelefono='" + numeroTelefono + '\'' +
                ", tipoUtente='" + tipoUtente + '\'' +
                '}';
    }
}
