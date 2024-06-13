package com.univaq.Agile.controller;

import com.univaq.Agile.controller.api.UtenteController;
import com.univaq.Agile.model.Utente;
import com.univaq.Agile.repository.UtenteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UtenteControllerTest {

    @Mock
    private UtenteRepository utenteRepository;

    @InjectMocks
    private UtenteController utenteController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllUtenti() {
        List<Utente> utenti = new ArrayList<>();
        utenti.add(new Utente("John", "Doe", "john@example.com", "password", "admin", "M", "123 Main St", "Italy", "1234567890"));
        when(utenteRepository.findAll()).thenReturn(utenti);

        List<Utente> result = utenteController.getAllUtenti();

        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getNome());
    }

    @Test
    void testGetUtenteById() {
        Long id = 1L;
        Utente utente = new Utente("John", "Doe", "john@example.com", "password", "admin", "M", "123 Main St", "Italy", "1234567890");
        when(utenteRepository.findById(id)).thenReturn(Optional.of(utente));

        Optional<Utente> result = utenteController.getUtenteById(id);

        assertEquals("John", result.get().getNome());
    }

    @Test
    void testGetUtenteByEmail() {
        String email = "john@example.com";
        Utente utente = new Utente("John", "Doe", email, "password", "admin", "M", "123 Main St", "Italy", "1234567890");
        when(utenteRepository.findByEmail(email)).thenReturn(utente);

        Utente result = utenteController.getUtenteByEmail(email);

        assertEquals("John", result.getNome());
    }

    @Test
    void testRegistraUtente() {
        Utente utente = new Utente("John", "Doe", "john@example.com", "password", "admin", "M", "123 Main St", "Italy", "1234567890");
        when(utenteRepository.save(utente)).thenReturn(utente);

        Utente result = utenteController.registraUtente(utente);

        assertEquals("John", result.getNome());
    }

    @Test
    void testCreateUtente() {
        Utente utente = new Utente("John", "Doe", "john@example.com", "password", "admin", "M", "123 Main St", "Italy", "1234567890");
        when(utenteRepository.save(utente)).thenReturn(utente);

        Utente result = utenteController.createUtente(utente);

        assertEquals("John", result.getNome());
    }

    @Test
    void testDeleteUtente() {
        Long id = 1L;
        utenteController.deleteUtente(id);
        verify(utenteRepository, times(1)).deleteById(id);
    }

    @Test
    void testGetUtenteLoggato() {
        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        SecurityContextHolder.setContext(securityContext);

        UserDetails userDetails = new User("john@example.com", "password", new ArrayList<>());
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(securityContext.getAuthentication()).thenReturn(authentication);

        Utente utente = new Utente("John", "Doe", "john@example.com", "password", "admin", "M", "123 Main St", "Italy", "1234567890");
        when(utenteRepository.findByEmail("john@example.com")).thenReturn(utente);

        Utente result = utenteController.getUtenteLoggato();

        assertEquals("John", result.getNome());
    }
}