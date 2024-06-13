package com.univaq.Agile.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class EventoTest {

    private Evento evento;

    @BeforeEach
    void setUp() {
        evento = new Evento();
    }

    @Test
    void testNomeEvento() {
        evento.setNomeEvento("Test Evento");
        assertEquals("Test Evento", evento.getNomeEvento());
    }


    @Test
    void testDataOraEvento() {
        LocalDateTime now = LocalDateTime.now();
        evento.setDataOraEvento(now);
        assertEquals(now, evento.getDataOraEvento());
    }

    @Test
    void testLuogo() {
        evento.setLuogo("Roma");
        assertEquals("Roma", evento.getLuogo());
    }

    @Test
    void testDescrizione() {
        evento.setDescrizione("Evento aperto a tutti i bambini");
        assertEquals("Evento aperto a tutti i bambini", evento.getDescrizione());
    }

    @Test
    void testDataOraCreazione() {
        LocalDateTime now = LocalDateTime.now();
        evento.setDataOraCreazione(now);
        assertEquals(now, evento.getDataOraCreazione());
    }

    @Test
    void testLinkImg() {
        evento.setLink_img("https://source.unsplash.com/random/300x400?seed=1234345");
        assertEquals("https://source.unsplash.com/random/300x400?seed=1234345", evento.getLink_img());
    }

    @Test
    void testStatoEvento() {
        evento.setStatoEvento("Futuro");
        assertEquals("Futuro", evento.getStatoEvento());
    }

    @Test
    void testId() {
        evento.setId(1L);
        assertEquals(1L, evento.getId());
    }

    @Test
    void testIdReferente() {
        evento.setIdReferente(1L);
        assertEquals(1L, evento.getIdReferente());
    }

    @Test
    void testAccettato() {
        evento.setAccettato("Accettato");
        assertEquals("Accettato", evento.getAccettato());
    }

    @Test
    void testPrezzo() {
        evento.setPrezzo("20");
        assertEquals("20", evento.getPrezzo());
    }

    @Test
    void testDurataEvento() {
        evento.setDurataEvento("2 ore");
        assertEquals("2 ore", evento.getDurataEvento());
    }

    @Test
    void testTemaEvento() {
        evento.setTemaEvento("Coltivazione");
        assertEquals("Coltivazione", evento.getTemaEvento());
    }
}

