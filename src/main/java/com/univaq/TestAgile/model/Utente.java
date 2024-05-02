package com.univaq.TestAgile.model;

import jakarta.persistence.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


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

    @Column(name = "tipo")
    private String tipo; // Aggiunto campo per il tipo di utente

    public Utente() {
    }

    public Utente(String nome, String cognome, String tipo) {
        this.nome = nome;
        this.cognome = cognome;
        this.tipo = tipo;
    }

    // Getters e setters per tutti i campi

    @Override
    public String toString() {
        return "Utente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
