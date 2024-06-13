package com.univaq.Agile.controller;


import com.univaq.Agile.controller.api.OrtoReferenteController;
import com.univaq.Agile.model.OrtoReferente;
import com.univaq.Agile.model.Zolla;
import com.univaq.Agile.repository.OrtoReferenteRepository;
import com.univaq.Agile.repository.ZollaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class OrtoReferenteControllerTest {

    @Mock
    private OrtoReferenteRepository ortoReferenteRepository;

    @Mock
    private ZollaRepository zollaRepository;

    @InjectMocks
    private OrtoReferenteController ortoReferenteController;

    private OrtoReferente ortoReferente1;
    private OrtoReferente ortoReferente2;
    private List<OrtoReferente> ortoReferenteList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        ortoReferente1 = new OrtoReferente("Orto 1", "Posizione 1", "Descrizione 1", 5, 5, 25, "Info 1", "Referente 1", "indefinito", null);
        ortoReferente2 = new OrtoReferente("Orto 2", "Posizione 2", "Descrizione 2", 3, 3, 9, "Info 2", "Referente 2", "accettato", null);
        ortoReferenteList = Arrays.asList(ortoReferente1, ortoReferente2);
    }

    @Test
    void testGetAllRichiesteOrti() {
        when(ortoReferenteRepository.findAll()).thenReturn(ortoReferenteList);

        List<OrtoReferente> result = ortoReferenteController.getAllRichiesteOrti();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Orto 1", result.get(0).getNome());
    }

    @Test
    void testGetRichiesteOrtiIndefinite() {
        when(ortoReferenteRepository.findByStato("indefinito")).thenReturn(Arrays.asList(ortoReferente1));

        List<OrtoReferente> result = ortoReferenteController.getRichiesteOrtiIndefinite();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Orto 1", result.get(0).getNome());
    }

    @Test
    void testGetRichiesteOrtiAccettate() {
        when(ortoReferenteRepository.findByStato("accettato")).thenReturn(Arrays.asList(ortoReferente2));

        List<OrtoReferente> result = ortoReferenteController.getRichiesteOrtiAccettate();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Orto 2", result.get(0).getNome());
    }

    @Test
    void testGetRichiesteOrtiRifiutate() {
        when(ortoReferenteRepository.findByStato("rifiutato")).thenReturn(Arrays.asList());

        List<OrtoReferente> result = ortoReferenteController.getRichiesteOrtiRifiutate();

        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    void testAccettaRichiestaOrto() {
        when(ortoReferenteRepository.findById(1L)).thenReturn(Optional.of(ortoReferente1));

        String result = ortoReferenteController.accettaRichiestaOrto(1L, "accettato");

        assertEquals("redirect:/admin/richieste-orto-referente", result);
        assertEquals("accettato", ortoReferente1.getStato());
        verify(ortoReferenteRepository, times(1)).save(any(OrtoReferente.class));
        verify(zollaRepository, times(25)).save(any(Zolla.class));
    }

    @Test
    void testGetOrtoById() {
        when(ortoReferenteRepository.findById(1L)).thenReturn(Optional.of(ortoReferente1));

        OrtoReferente result = ortoReferenteController.getOrtoById(1L);

        assertNotNull(result);
        assertEquals("Orto 1", result.getNome());
    }

    @Test
    void testGetOrtoByIdRef() {
        when(ortoReferenteRepository.findByUtente_Id(1L)).thenReturn(Arrays.asList(ortoReferente1));

        List<OrtoReferente> result = ortoReferenteController.getOrtoByIdRef(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Orto 1", result.get(0).getNome());
    }

    @Test
    void testInsertZolleVuote() {
        ortoReferenteController.insertZolleVuote(ortoReferente1);

        verify(zollaRepository, times(25)).save(any(Zolla.class));
    }
}
