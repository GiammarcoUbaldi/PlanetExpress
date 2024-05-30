package com.univaq.TestAgile.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrtoTest {

    private OrtoReferente ortoReferente;
    private Utente utente;

    @BeforeEach
    void setUp() {
        utente = new Utente();
        ortoReferente = new OrtoReferente("Orto Test", "Posizione Test", "Descrizione Test", 10, 20, 30, "Info Aggiuntive Test", "Referente Test", "Attivo", utente);
    }

    @Test
    void testGetNome() {
        assertEquals("Orto Test", ortoReferente.getNome());
    }

    @Test
    void testSetNome() {
        ortoReferente.setNome("Nuovo Nome");
        assertEquals("Nuovo Nome", ortoReferente.getNome());
    }

    @Test
    void testGetPosizione() {
        assertEquals("Posizione Test", ortoReferente.getPosizione());
    }

    @Test
    void testSetPosizione() {
        ortoReferente.setPosizione("Posizione Test");
        assertEquals("Posizione Test", ortoReferente.getPosizione());
    }

    @Test
    void testGetDescrizione() {
        assertEquals("Descrizione Test", ortoReferente.getDescrizione());
    }

    @Test
    void testSetDescrizione() {
        ortoReferente.setDescrizione("Nuova Descrizione");
        assertEquals("Nuova Descrizione", ortoReferente.getDescrizione());
    }

    @Test
    void testGetDimensioneX() {
        assertEquals(10, ortoReferente.getDimensioneX());
    }

    @Test
    void testSetDimensioneX() {
        ortoReferente.setDimensioneX(15);
        assertEquals(15, ortoReferente.getDimensioneX());
    }

    @Test
    void testGetDimensioneY() {
        assertEquals(20, ortoReferente.getDimensioneY());
    }

    @Test
    void testSetDimensioneY() {
        ortoReferente.setDimensioneY(25);
        assertEquals(25, ortoReferente.getDimensioneY());
    }

    @Test
    void testGetNumeroZolle() {
        assertEquals(30, ortoReferente.getNumeroZolle());
    }

    @Test
    void testSetNumeroZolle() {
        ortoReferente.setNumeroZolle(35);
        assertEquals(35, ortoReferente.getNumeroZolle());
    }

    @Test
    void testGetInfoAggiuntive() {
        assertEquals("Info Aggiuntive Test", ortoReferente.getInfoAggiuntive());
    }

    @Test
    void testSetInfoAggiuntive() {
        ortoReferente.setInfoAggiuntive("Orto non ospita più 10 zolle");
        assertEquals("Orto non ospita più 10 zolle", ortoReferente.getInfoAggiuntive());
    }

    @Test
    void testGetNomeReferente() {
        assertEquals("Referente Test", ortoReferente.getNomeReferente());
    }

    @Test
    void testSetNomeReferente() {
        ortoReferente.setNomeReferente("Nuovo Referente");
        assertEquals("Nuovo Referente", ortoReferente.getNomeReferente());
    }

    @Test
    void testGetStato() {
        assertEquals("Attivo", ortoReferente.getStato());
    }

    @Test
    void testSetStato() {
        ortoReferente.setStato("Inattivo");
        assertEquals("Inattivo", ortoReferente.getStato());
    }

    @Test
    void testGetUtente() {
        assertEquals(utente, ortoReferente.getUtente());
    }

    @Test
    void testSetUtente() {
        Utente nuovoUtente = new Utente();
        ortoReferente.setUtente(nuovoUtente);
        assertEquals(nuovoUtente, ortoReferente.getUtente());
    }

    @Test
    void testZolle() {
        ortoReferente.setNumeroZolle(6);
        assertEquals(6, ortoReferente.getNumeroZolle());
    }
}
