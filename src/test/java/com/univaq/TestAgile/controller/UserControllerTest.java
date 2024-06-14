package com.univaq.TestAgile.controller;

import com.univaq.TestAgile.controller.api.EventoController;
import com.univaq.TestAgile.controller.api.OrtoReferenteController;
import com.univaq.TestAgile.controller.api.UtenteController;
import com.univaq.TestAgile.controller.api.ZollaController;
import com.univaq.TestAgile.controller.utente.UserController;
import com.univaq.TestAgile.model.Evento;
import com.univaq.TestAgile.model.Utente;
import com.univaq.TestAgile.model.Zolla;
import com.univaq.TestAgile.repository.PartecipazioneRepository;
import com.univaq.TestAgile.repository.UtenteRepository;
import com.univaq.TestAgile.repository.ZollaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private PartecipazioneRepository partecipazioneRepository;

    @Mock
    private UtenteController utenteController;

    @Mock
    private EventoController eventoController;

    @Mock
    private UtenteRepository utenteRepository;

    @Mock
    private OrtoReferenteController ortoReferenteController;

    @Mock
    private ZollaController zollaController;

    @Mock
    private ZollaRepository zollaRepository;

    @Mock
    private Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    // Test per mostraDettagliEvento
    @Test
    void testMostraDettagliEvento() {
        long id = 1L;
        Evento evento = new Evento();
        Utente utente = new Utente();
        utente.setId(1L);

        when(eventoController.getEventoById(id)).thenReturn(evento);
        when(utenteController.getUtenteLoggato()).thenReturn(utente);
        when(partecipazioneRepository.existsByEventoIdAndUtenteId(id, utente.getId())).thenReturn(true);

        String result = userController.mostraDettagliEvento(model, id);

        verify(model).addAttribute("datiDettagli", evento);
        verify(model).addAttribute("utentePrenotato", true);
        verifyNoMoreInteractions(model);
        assertEquals("utente/dettagliEventoUser", result);
    }


    // Test per raccogliNellaZolla
    @Test
    void testRaccogliNellaZolla() {
        Long zollaId = 1L;
        Zolla zollaPiantata = new Zolla();

        when(zollaRepository.findById(zollaId)).thenReturn(java.util.Optional.of(zollaPiantata));

        String result = userController.raccogliNellaZolla(model, zollaId);

        assertEquals("redirect:/user/ortoOccupato", result);
        verify(zollaRepository).findById(zollaId);
        verify(zollaRepository).save(zollaPiantata);
    }


}
