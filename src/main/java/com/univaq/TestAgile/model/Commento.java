package com.univaq.TestAgile.model;


import jakarta.persistence.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.LocalDate;
import java.time.LocalDateTime;


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

    @Column(name = "username")
    private String username;

    @Column(name = "descrizione")
    private String descrizione;

    @Column(name = "dataCreazione")
    private LocalDateTime dataCreazione;

    public Commento() {
    }

    public Commento(Long id, Post post, String username, String descrizione) {
        this.id = id;
        this.post = post;
        this.username = username;
        this.descrizione = descrizione;
        this.dataCreazione = LocalDateTime.now();
    }

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

    public LocalDateTime getDataCreazione() {
        return dataCreazione;
    }

    public void setDataCreazione(LocalDateTime dataCreazione) {
        this.dataCreazione = dataCreazione;
    }
}
