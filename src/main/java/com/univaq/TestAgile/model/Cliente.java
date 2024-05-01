package com.univaq.TestAgile.model;

import jakarta.persistence.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories
@Entity
@Table(name = "clienti") // Usiamo "clienti" per tradurre customer
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome") // Usiamo "nome" per tradurre firstName
    private String nomeX;

    @Column(name = "cognome") // Usiamo "cognome" per tradurre lastName
    private String cognomeX;

    public Cliente(String giovanni, String rossi) {
    }

    public Cliente() {

    }

    public String getNome() {
        return nomeX;
    }

    public void setNome(String nome) {
        this.nomeX = nome;
    }

    public String getCognome() {
        return cognomeX;
    }

    public void setCognome(String cognome) {
        this.cognomeX = cognome;
    }
    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nomeX + '\'' +
                ", cognome='" + cognomeX + '\'' +
                '}';
    }
}