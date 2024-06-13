package com.univaq.TestAgile.model;


import jakarta.persistence.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;


@EnableJpaRepositories
@Entity
@Table
public class Commento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;
    @Column(name = "username")
    private String username;

    @Column(name = "descrizione")
    private String descrizione;

    @Column(name = "dataCreazione")
    private LocalDateTime dataCreazione;

    public Commento() {
    }

    public Commento(Post post, Utente utente, String username, String descrizione) {
        this.post = post;
        this.utente = utente;
        this.username = username;
        this.descrizione = descrizione;
    }

    @PrePersist
    protected void onCreate() {
        this.dataCreazione = LocalDateTime.now();
    }

    // Getter e Setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Date getDataCreazione() {
        return Date.from(dataCreazione.atZone(ZoneId.systemDefault()).toInstant());
    }

    public void setDataCreazione(LocalDateTime dataCreazione) {
        this.dataCreazione = dataCreazione;
    }
}
