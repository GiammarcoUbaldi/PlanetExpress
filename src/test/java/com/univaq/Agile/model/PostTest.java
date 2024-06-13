package com.univaq.Agile.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PostTest {

    private Post post;
    private Utente utente;

    @BeforeEach
    public void setUp() {
        utente = new Utente();
        utente.setId(1L);
        utente.setNome("Test User");

        post = new Post("testuser", "tipo1", "titolo1", "descrizione1", utente);
    }

    @Test
    public void testPostCreation() {
        assertNotNull(post);
        assertEquals("testuser", post.getUsername());
        assertEquals("tipo1", post.getTipo());
        assertEquals("titolo1", post.getTitolo());
        assertEquals("descrizione1", post.getDescrizione());
        assertEquals(utente, post.getUtente());
    }

    @Test
    public void testOnCreate() {
        post.onCreate();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime postCreationDate = post.getDataCreazione().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        assertNotNull(post.getDataCreazione());
        assertTrue(now.minusSeconds(1).isBefore(postCreationDate) && now.plusSeconds(1).isAfter(postCreationDate));
    }

    @Test
    public void testSettersAndGetters() {
        post.setId(1L);
        assertEquals(1L, post.getId());

        post.setUsername("newusername");
        assertEquals("newusername", post.getUsername());

        post.setTipo("newtipo");
        assertEquals("newtipo", post.getTipo());

        post.setTitolo("newtitolo");
        assertEquals("newtitolo", post.getTitolo());

        post.setDescrizione("newdescrizione");
        assertEquals("newdescrizione", post.getDescrizione());

        LocalDateTime newDate = LocalDateTime.of(2023, 5, 30, 12, 0);
        post.setDataCreazione(newDate);
        Date date = Date.from(newDate.atZone(ZoneId.systemDefault()).toInstant());
        assertEquals(date, post.getDataCreazione());

        Utente newUtente = new Utente();
        newUtente.setId(2L);
        newUtente.setNome("New User");
        post.setUtente(newUtente);
        assertEquals(newUtente, post.getUtente());
    }

    @Test
    public void testCommenti() {
        Commento commento1 = new Commento();
        commento1.setId(1L);
        commento1.setDescrizione("commento1");
        commento1.setPost(post);

        Commento commento2 = new Commento();
        commento2.setId(2L);
        commento2.setDescrizione("commento2");
        commento2.setPost(post);

        post.setCommenti(List.of(commento1, commento2));

        assertNotNull(post.getCommenti());
        assertEquals(2, post.getCommenti().size());
        assertEquals(commento1, post.getCommenti().get(0));
        assertEquals(commento2, post.getCommenti().get(1));
    }
}

