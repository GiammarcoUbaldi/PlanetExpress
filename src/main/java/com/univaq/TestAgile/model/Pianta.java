package com.univaq.TestAgile.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Pianta")
public class Pianta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String tipo;

    public Pianta(String nome, String tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }

    public Pianta() {

    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
