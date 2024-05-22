package com.univaq.TestAgile.model;


import jakarta.persistence.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.LocalDate;

@EnableJpaRepositories
@Entity
@Table
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "titolo")
    private String titolo;

    @Column(name = "descrizione")
    private String descrizione;

    @Column(name = "dataCreazione")
    private LocalDate dataCreazione;

    public Post() {
    }

    public Post(Long id, String username, String tipo, String titolo, String descrizione, LocalDate dataCreazione) {
        this.id = id;
        this.username = username;
        this.tipo = tipo;
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.dataCreazione = dataCreazione;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public LocalDate getDataCreazione() {
        return dataCreazione;
    }

    public void setDataCreazione(LocalDate dataCreazione) {
        this.dataCreazione = dataCreazione;
    }
}
