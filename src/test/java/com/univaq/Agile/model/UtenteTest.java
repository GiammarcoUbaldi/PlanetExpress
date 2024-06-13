package com.univaq.Agile.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UtenteTest {

    private Utente utente;

    @BeforeEach
    public void setUp() {
        // Initialize the Utente object before each test
        utente = new Utente("John", "Doe", "john.doe@example.com", "password123", "admin", "M", "123 Main St", "Italy", "1234567890");
    }

    @Test
    public void testUtenteCreation() {
        // Test that the Utente object is created correctly
        assertNotNull(utente);
        assertEquals("John", utente.getNome());
        assertEquals("Doe", utente.getCognome());
        assertEquals("john.doe@example.com", utente.getEmail());
        assertEquals("password123", utente.getPassword());
        assertEquals("admin", utente.getTipoUtente());
        assertEquals("M", utente.getSesso());
        assertEquals("123 Main St", utente.getIndirizzo());
        assertEquals("Italy", utente.getNazione());
        assertEquals("1234567890", utente.getNumeroTelefono());
    }

    @Test
    public void testSetAndGetCommenti() {
        // Test the setting and getting of commenti
        List<Commento> commenti = new ArrayList<>();
        Commento commento1 = new Commento();
        Commento commento2 = new Commento();
        commenti.add(commento1);
        commenti.add(commento2);

        utente.setCommenti(commenti);
        assertEquals(2, utente.getCommenti().size());
    }

    @Test
    public void testSetters() {
        // Test the setter methods
        utente.setNome("Jane");
        assertEquals("Jane", utente.getNome());

        utente.setCognome("Smith");
        assertEquals("Smith", utente.getCognome());

        utente.setEmail("jane.smith@example.com");
        assertEquals("jane.smith@example.com", utente.getEmail());

        utente.setPassword("newpassword");
        assertEquals("newpassword", utente.getPassword());

        utente.setTipoUtente("user");
        assertEquals("user", utente.getTipoUtente());

        utente.setSesso("F");
        assertEquals("F", utente.getSesso());

        utente.setIndirizzo("456 Another St");
        assertEquals("456 Another St", utente.getIndirizzo());

        utente.setNazione("USA");
        assertEquals("USA", utente.getNazione());

        utente.setNumeroTelefono("0987654321");
        assertEquals("0987654321", utente.getNumeroTelefono());
    }

    @Test
    public void testToString() {
        // Test the toString method
        String expected = "Utente{id=null, nome='John', cognome='Doe', email='john.doe@example.com', password='password123', sesso='M', indirizzo='123 Main St', nazione='Italy', numeroTelefono='1234567890', tipoUtente='admin'}";
        assertEquals(expected, utente.toString());
    }
}