package com.univaq.TestAgile.controller;

import com.univaq.TestAgile.controller.api.EventoController;
import com.univaq.TestAgile.controller.api.OrtoReferenteController;
import com.univaq.TestAgile.controller.api.UtenteController;
import com.univaq.TestAgile.controller.api.ZollaController;
import com.univaq.TestAgile.controller.referente.ReferenteController;
import com.univaq.TestAgile.model.Evento;
import com.univaq.TestAgile.model.OrtoReferente;
import com.univaq.TestAgile.model.Utente;
import com.univaq.TestAgile.model.Zolla;
import com.univaq.TestAgile.repository.OrtoReferenteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class ReferenteControllerTest {

    @InjectMocks
    private ReferenteController referenteController;

    @Mock
    private OrtoReferenteController ortoReferenteController;

    @Mock
    private ZollaController zollaController;

    @Mock
    private EventoController eventoController;

    @Mock
    private OrtoReferenteRepository ortoReferenteRepository;

    @Mock
    private UtenteController utenteController;

    @Mock
    private Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Test per mostraOrtoReferente
    @Test
    void testMostraOrtoReferente() {
        Utente utente = new Utente();
        OrtoReferente ortoReferente = new OrtoReferente();

        when(utenteController.getUtenteLoggato()).thenReturn(utente);
        when(ortoReferenteRepository.findOrtoReferenteByUtenteAndId(any(Utente.class), eq(1L))).thenReturn(ortoReferente);
        when(ortoReferenteController.getOrtoById(1L)).thenReturn(ortoReferente);
        when(zollaController.getListZolleByOrto(ortoReferente)).thenReturn(Collections.emptyList());

        String result = referenteController.mostraOrtoReferente(model, 1L);

        verify(model).addAttribute("ortoReferente", ortoReferente);
        verify(model).addAttribute("zolle", Collections.emptyList());
        verifyNoMoreInteractions(model);
        assertEquals("/referente/orto/visualizzaOrtoReferente", result);
    }

    // Test per mostraEventiReferente
    @Test
    void testMostraEventiReferente() {
        long id = 1L;
        List<Evento> eventiRefe = Collections.singletonList(new Evento());
        when(eventoController.getEventiByIdRef(id)).thenReturn(eventiRefe);
        when(eventoController.getEventiFuturiRef(id)).thenReturn(eventiRefe);
        when(eventoController.getEventiAccettatiRef(id)).thenReturn(eventiRefe);

        String result = referenteController.mostraEventiReferente(model, id);

        verify(model).addAttribute("EventiRefe", eventiRefe);
        verify(model).addAttribute("EventiRefeFuturi", eventiRefe);
        verify(model).addAttribute("EventiRefeInSospeso", eventiRefe);
        verifyNoMoreInteractions(model);
        assertEquals("referente/listaEventiReferente", result);
    }

    // Test per mostraDettagliEvento
    @Test
    void testMostraDettagliEvento() {
        long id = 1L;
        Evento evento = new Evento();
        when(eventoController.getEventoById(id)).thenReturn(evento);

        String result = referenteController.mostraDettagliEvento(model, id);

        verify(model).addAttribute("datiDettagli", evento);
        verifyNoMoreInteractions(model);
        assertEquals("referente/dettagliEventoReferente", result);
    }

    // Test per dashboard
    @Test
    void testDashboard() {
        Utente utente = new Utente();
        OrtoReferente ortoReferente = new OrtoReferente();
        List<OrtoReferente> ortiDelReferente = Collections.singletonList(ortoReferente);

        when(utenteController.getUtenteLoggato()).thenReturn(utente);
        when(ortoReferenteRepository.findByUtente(utente)).thenReturn(ortiDelReferente);

        String result = referenteController.dashboard(model);

        verify(model).addAttribute("ortoDelReferente", ortoReferente);
        verifyNoMoreInteractions(model);
        assertEquals("referente/dashboardReferente", result);
    }

    // Test per mostraFormRichiestaOrtoReferente
    @Test
    void testMostraFormRichiestaOrtoReferente() {
        String result = referenteController.mostraFormRichiestaOrtoReferente(model);

        assertEquals("/referente/formRichiestaOrtoReferete", result);
    }

    // Test per mostraFormRichiestaEventoReferente
    @Test
    void testMostraFormRichiestaEventoReferente() {
        String result = referenteController.mostraFormRichiestaEventoReferente(model);

        assertEquals("/referente/formRichiestaEventoReferente", result);
    }

}

