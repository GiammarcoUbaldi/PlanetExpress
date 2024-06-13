package com.univaq.Agile.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class CommentoTest {

    private Commento commento;
    private Post post;
    private Utente utente;

    @BeforeEach
    public void setUp() {
        post = new Post();
        post.setId(1L);
        post.setTitolo("Test Post");

        utente = new Utente();
        utente.setId(1L);
        utente.setNome("Test User");

        commento = new Commento(post, utente, "testuser", "descrizione1");
    }

    @Test
    public void testCommentoCreation() {
        assertNotNull(commento);
        assertEquals(post, commento.getPost());
        assertEquals(utente, commento.getUtente());
        assertEquals("testuser", commento.getUsername());
        assertEquals("descrizione1", commento.getDescrizione());
    }

    @Test
    public void testOnCreate() {
        commento.onCreate();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime commentCreationDate = commento.getDataCreazione().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        assertNotNull(commento.getDataCreazione());
        assertTrue(now.minusSeconds(1).isBefore(commentCreationDate) && now.plusSeconds(1).isAfter(commentCreationDate));
    }

    @Test
    public void testSettersAndGetters() {
        commento.setId(1L);
        assertEquals(1L, commento.getId());

        Post newPost = new Post();
        newPost.setId(2L);
        newPost.setTitolo("New Post");
        commento.setPost(newPost);
        assertEquals(newPost, commento.getPost());

        Utente newUtente = new Utente();
        newUtente.setId(2L);
        newUtente.setNome("New User");
        commento.setUtente(newUtente);
        assertEquals(newUtente, commento.getUtente());

        commento.setUsername("newusername");
        assertEquals("newusername", commento.getUsername());

        commento.setDescrizione("newdescrizione");
        assertEquals("newdescrizione", commento.getDescrizione());

        LocalDateTime newDate = LocalDateTime.of(2023, 5, 30, 12, 0);
        commento.setDataCreazione(newDate);
        Date date = Date.from(newDate.atZone(ZoneId.systemDefault()).toInstant());
        assertEquals(date, commento.getDataCreazione());
    }
}

