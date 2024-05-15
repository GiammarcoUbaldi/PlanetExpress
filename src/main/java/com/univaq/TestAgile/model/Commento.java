package com.univaq.TestAgile.model;


import jakarta.persistence.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@Entity
@Table
public class Commento {

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

    public Commento() {
    }

    public Commento(String username, String tipo, String titolo, String descrizione) {
        this.username = username;
        this.tipo = tipo;
        this.titolo = titolo;
        this.descrizione = descrizione;
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

}
