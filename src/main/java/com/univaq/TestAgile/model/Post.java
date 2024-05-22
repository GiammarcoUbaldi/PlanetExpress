package com.univaq.TestAgile.model;


import jakarta.persistence.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
    private LocalDateTime dataCreazione;

    @OneToMany(mappedBy = "post")
    private List<Commento> commenti;

    public Post() {
    }

    public Post(Long id, String username, String tipo, String titolo, String descrizione, List<Commento> commenti) {
        this.id = id;
        this.username = username;
        this.tipo = tipo;
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.commenti = commenti;
        this.dataCreazione = LocalDateTime.now();
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

    public LocalDateTime getDataCreazione() {
        return dataCreazione;
    }

    public void setDataCreazione(LocalDateTime dataCreazione) {
        this.dataCreazione = dataCreazione;
    }

    public List<Commento> getCommenti() {
        return commenti;
    }

    public void setCommenti(List<Commento> commenti) {
        this.commenti = commenti;
    }
}
