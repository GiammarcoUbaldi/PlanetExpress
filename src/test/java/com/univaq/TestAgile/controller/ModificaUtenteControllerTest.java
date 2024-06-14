package com.univaq.TestAgile.controller;

import com.univaq.TestAgile.model.Utente;
import com.univaq.TestAgile.repository.UtenteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import com.univaq.TestAgile.controller.api.ModificaUtenteController;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class ModificaUtenteControllerTest {

    @Mock
    private UtenteRepository utenteRepository;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private ModificaUtenteController modificaUtenteController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    void testModificaUtente() {
//        // Arrange
//        Utente utente = new Utente();
//        when(bindingResult.hasErrors()).thenReturn(false);
//
//        // Act
//        String viewName = modificaUtenteController.modificaUtente(model, utente, bindingResult);
//
//        // Assert
//        assertEquals("redirect:/dashboard", viewName);
//        verify(utenteRepository, times(1)).save(utente);
//        verify(model, times(1)).addAttribute("success", "ok");
//        verify(model, times(1)).addAttribute("utente", utente);
//    }

//    @Test
//    void testModificaUtenteWithError() {
//        // Arrange
//        Utente utente = new Utente();
//        when(bindingResult.hasErrors()).thenReturn(true);
//
//        // Act
//        String viewName = modificaUtenteController.modificaUtente(model, utente, bindingResult);
//
//        // Assert
//        assertEquals("/autenticazione/modificaDati", viewName);
//        verify(utenteRepository, never()).save(utente);
//        verify(model, times(1)).addAttribute("success", "no");
//    }
}

