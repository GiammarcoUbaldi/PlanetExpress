package com.univaq.TestAgile.model;


import jakarta.persistence.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

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
    private Post post_id;

    @Column(name = "username")
    private String username;

    @Column(name = "descrizione")
    private String descrizione;

    @Column(name = "dataCreazione")
    private Date dataCreazione;

    public Commento() {
    }

    public Commento(Long id, Post post_id, String username, String descrizione, Date dataCreazione) {
        this.id = id;
        this.post_id = post_id;
        this.username = username;
        this.descrizione = descrizione;
        this.dataCreazione = dataCreazione;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Post getPost_id() {
        return post_id;
    }

    public void setPost_id(Post post_id) {
        this.post_id = post_id;
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
        return dataCreazione;
    }

    public void setDataCreazione(Date dataCreazione) {
        this.dataCreazione = dataCreazione;
    }
}
