package com.univaq.TestAgile.controller;
import com.univaq.TestAgile.controller.api.EventoController;
import com.univaq.TestAgile.model.Evento;
import com.univaq.TestAgile.repository.EventoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EventoControllerTest {

    @Mock
    private EventoRepository eventoRepository;

    @InjectMocks
    private EventoController eventoController;

    private Evento evento1;
    private Evento evento2;
    private List<Evento> eventoList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        evento1 = new Evento("Evento 1", "Referente 1", 1L, LocalDateTime.now().plusDays(1), "Luogo 1", "Descrizione 1", LocalDateTime.now(), "link1.jpg", "10.00", "2 ore", "Tema 1", "Futuro", "Accettato");
        evento2 = new Evento("Evento 2", "Referente 2", 2L, LocalDateTime.now().plusDays(2), "Luogo 2", "Descrizione 2", LocalDateTime.now(), "link2.jpg", "15.00", "3 ore", "Tema 2", "Futuro", "In sospeso");
        eventoList = Arrays.asList(evento1, evento2);
    }

    @Test
    void testGetEventiAccettati() {
        when(eventoRepository.findByAccettato("Accettato")).thenReturn(Arrays.asList(evento1));

        List<Evento> result = eventoController.getEventiAccettati();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Evento 1", result.get(0).getNomeEvento());
    }

    @Test
    void testGetEventiRifiutati() {
        when(eventoRepository.findByAccettato("Rifiutato")).thenReturn(Arrays.asList(evento2));

        List<Evento> result = eventoController.getEventiRifiutati();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Evento 2", result.get(0).getNomeEvento());
    }

    @Test
    void testGetEventiInSospeso() {
        when(eventoRepository.findByAccettato("In sospeso")).thenReturn(Arrays.asList(evento2));

        List<Evento> result = eventoController.getEventiInSospeso();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Evento 2", result.get(0).getNomeEvento());
    }

    @Test
    void testGetEventiProposti() {
        when(eventoRepository.findByIdReferente(1L)).thenReturn(Arrays.asList(evento1));

        List<Evento> result = eventoController.getEventiProposti(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Evento 1", result.get(0).getNomeEvento());
    }

    @Test
    void testGetEventiAccettatiRef() {
        when(eventoRepository.findByAccettato("In sospeso")).thenReturn(eventoList);

        List<Evento> result = eventoController.getEventiAccettatiRef(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Evento 1", result.get(0).getNomeEvento());
    }

    @Test
    void testGetEventiFuturiRef() {
        when(eventoRepository.findByAccettato("Accettato")).thenReturn(eventoList);

        List<Evento> result = eventoController.getEventiFuturiRef(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Evento 1", result.get(0).getNomeEvento());
    }

    @Test
    void testCreateEvento() {
        when(eventoRepository.save(any(Evento.class))).thenReturn(evento1);

        Evento result = eventoController.createEvento(evento1);

        assertNotNull(result);
        assertEquals("Evento 1", result.getNomeEvento());
    }

    @Test
    void testUpdateStato() {
        when(eventoRepository.findById(1L)).thenReturn(Optional.of(evento1));

        String result = eventoController.updateStato(1L, "Accettato");

        assertEquals("redirect:/mostraEventi", result);
        verify(eventoRepository, times(1)).save(any(Evento.class));
    }

    @Test
    void testGetEventoById() {
        when(eventoRepository.findById(1L)).thenReturn(Optional.of(evento1));

        Evento result = eventoController.getEventoById(1L);

        assertNotNull(result);
        assertEquals("Evento 1", result.getNomeEvento());
    }

    @Test
    void testGetEventiByIdRef() {
        when(eventoRepository.findByIdReferente(1L)).thenReturn(Arrays.asList(evento1));

        List<Evento> result = eventoController.getEventiByIdRef(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Evento 1", result.get(0).getNomeEvento());
    }

    @Test
    void testGetEventi3() {
        when(eventoRepository.findByIdReferente(1L)).thenReturn(eventoList);

        List<Evento> result = eventoController.getEventi3(1L);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Evento 1", result.get(0).getNomeEvento());
        assertEquals("Evento 2", result.get(1).getNomeEvento());
    }

    @Test
    void testGetEventiInSospesoRef() {
        when(eventoRepository.findByAccettato("In sospeso")).thenReturn(Arrays.asList(evento2));

        List<Evento> result = eventoController.getEventiInSospesoRef();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Evento 2", result.get(0).getNomeEvento());
    }
}
