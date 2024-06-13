package com.univaq.Agile.controller;

import com.univaq.Agile.controller.api.OrtoReferenteController;
import com.univaq.Agile.controller.api.ZollaController;
import com.univaq.Agile.model.OrtoReferente;
import com.univaq.Agile.model.Zolla;
import com.univaq.Agile.repository.ZollaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ZollaControllerTest {

    @Mock
    private OrtoReferenteController ortoReferenteController;

    @Mock
    private ZollaRepository zollaRepository;

    @InjectMocks
    private ZollaController zollaController;

    private OrtoReferente ortoReferente;
    private Zolla zolla1;
    private Zolla zolla2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        ortoReferente = new OrtoReferente("Orto 1", "Posizione 1", "Descrizione 1", 5, 5, 25, "Info 1", "Referente 1", "indefinito", null);
        zolla1 = new Zolla("Zolla_1", "sabbioso", null, null, null, null, null, null, ortoReferente);
        zolla2 = new Zolla("Zolla_2", "argilloso", null, null, null, null, null, null, ortoReferente);
    }

    @Test
    void testGetListZolleByIdOrto() {
        when(ortoReferenteController.getOrtoById(1L)).thenReturn(ortoReferente);
        when(zollaRepository.findByOrto(ortoReferente)).thenReturn(Arrays.asList(zolla1, zolla2));

        List<Zolla> result = zollaController.getListZolleByIdOrto(1L);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Zolla_1", result.get(0).getNome());
        assertEquals("Zolla_2", result.get(1).getNome());

        verify(ortoReferenteController, times(1)).getOrtoById(1L);
        verify(zollaRepository, times(1)).findByOrto(ortoReferente);
    }

    @Test
    void testGetListZolleByOrto() {
        when(zollaRepository.findByOrto(ortoReferente)).thenReturn(Arrays.asList(zolla1, zolla2));

        List<Zolla> result = zollaController.getListZolleByOrto(ortoReferente);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Zolla_1", result.get(0).getNome());
        assertEquals("Zolla_2", result.get(1).getNome());

        verify(zollaRepository, times(1)).findByOrto(ortoReferente);
    }
}

