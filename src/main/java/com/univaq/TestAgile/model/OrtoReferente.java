package com.univaq.TestAgile.model;

import jakarta.persistence.*;
import org.springframework.boot.context.properties.bind.DefaultValue;
    import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@EnableJpaRepositories
@Entity
@Table(name = "orto_referente")
public class OrtoReferente {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "orto")
    private List<Zolla> zolle;

    @Column(name = "nome")
    private String nome;

    @Column(name = "posizione")
    private String posizione;

    @Column(name = "descrizione")
    private String descrizione;

    @Column(name = "dimensione_x")
    private Integer dimensioneX;

    @Column(name = "dimensione_y")
    private Integer dimensioneY;

    @Column(name = "numero_zolle")
    private Integer numeroZolle;

    @Column(name = "info_aggiuntive")
    private String infoAggiuntive;

    @Column(name = "nome_referente")
    private String nomeReferente;

    @Column(name = "stato")
    private String stato;

    public OrtoReferente(String nome, String posizione, String descrizione, Integer dimensioneX, Integer dimensioneY, Integer numeroZolle, String infoAggiuntive, String nome_referente, String stato) {
        this.nome = nome;
        this.posizione = posizione;
        this.descrizione = descrizione;
        this.dimensioneX = dimensioneX;
        this.dimensioneY = dimensioneY;
        this.numeroZolle = numeroZolle;
        this.infoAggiuntive = infoAggiuntive;
        this.nomeReferente = nome_referente;
        this.stato = stato;
    }

    public OrtoReferente() {
        this.stato = "indefinito";

    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPosizione() {
        return posizione;
    }

    public void setPosizione(String posizione) {
        this.posizione = posizione;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Integer getDimensioneX() {
        return dimensioneX;
    }

    public void setDimensioneX(Integer dimensioneX) {
        this.dimensioneX = dimensioneX;
    }

    public Integer getDimensioneY() {
        return dimensioneY;
    }

    public void setDimensioneY(Integer dimensioneY) {
        this.dimensioneY = dimensioneY;
    }

    public Integer getNumeroZolle() {
        return numeroZolle;
    }

    public void setNumeroZolle(Integer numeroZolle) {
        this.numeroZolle = numeroZolle;
    }

    public String getInfoAggiuntive() {
        return infoAggiuntive;
    }

    public void setInfoAggiuntive(String infoAggiuntive) {
        this.infoAggiuntive = infoAggiuntive;
    }

    public String getNomeReferente() {
        return nomeReferente;
    }

    public void setNomeReferente(String nome_referente) {
        this.nomeReferente = nome_referente;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }
}
