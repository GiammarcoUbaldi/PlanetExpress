package com.univaq.TestAgile.model;

import jakarta.persistence.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cosa_da_fare")
    private String cosaDaFare;

    @Column(name = "eseguita")
    private Integer eseguita;

    @ManyToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;

    public Task(String cosaDaFare, Integer eseguita) {
        this.cosaDaFare = cosaDaFare;
        this.eseguita = eseguita;
    }

    public Task() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCosaDaFare() {
        return cosaDaFare;
    }

    public void setCosaDaFare(String cosaDaFare) {
        this.cosaDaFare = cosaDaFare;
    }

    public Integer getCheck() {
        return eseguita;
    }

    public void setCheck(Integer check) {
        this.eseguita = check;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", cosaDaFare='" + cosaDaFare + '\'' +
                ", eseguita=" + eseguita +
                ", utente=" + utente +
                '}';
    }

}
