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

    @Column(name = "password")
    private String password;

    @Column(name = "tipo")
    private String tipo; // Aggiunto campo per il tipo di utente

    public Utente() {
    }

    public Utente(String nome, String password, String tipo) {
        this.nome = nome;
        this.password = password;
        this.tipo = tipo;
    }

    // Getters e setters per tutti i campi

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + password + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
