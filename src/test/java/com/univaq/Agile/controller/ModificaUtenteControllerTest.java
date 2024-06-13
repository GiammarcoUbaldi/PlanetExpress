package com.univaq.Agile.controller;


import com.univaq.Agile.controller.api.ModificaUtenteController;
import com.univaq.Agile.model.Utente;
import com.univaq.Agile.repository.OrtoReferenteRepository;
import com.univaq.Agile.repository.UtenteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ModificaUtenteControllerTest {

    @Mock
    UtenteRepository utenteRepository;

    @Mock
    OrtoReferenteRepository ortoReferenteRepository;

    @InjectMocks
    ModificaUtenteController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testMostraModificaUtente() {
        // Given
        Long idUtente = 1L;
        Utente utente = new Utente();
        utente.setId(idUtente);
        when(utenteRepository.findById(idUtente)).thenReturn(Optional.of(utente));
        Model model = mock(Model.class);

        // When
        String viewName = controller.mostraModificaUtente(idUtente, model);

        // Then
        assertEquals("/autenticazione-utente/form", viewName);
        verify(utenteRepository, times(1)).findById(idUtente);
        verifyNoMoreInteractions(utenteRepository);
    }

   /* @Test
    void testModificaUtente() {
        // Given
        Utente utente = new Utente();
        Long idOrto = 1L;
        OrtoReferente ortoReferente = new OrtoReferente();
        ortoReferente.setId(idOrto);
        utente.setOrtoOccupato(ortoReferente);
        BindingResult bindingResult = mock(BindingResult.class);
        Model model = mock(Model.class);

        // When
        String viewName = controller.modificaUtente(model, utente, bindingResult);

        // Then
        assertEquals("redirect:/dashboard", viewName);
        assertEquals(ortoReferente, utente.getOrtoOccupato());
        verify(ortoReferenteRepository, times(1)).findById(idOrto);
        verify(utenteRepository, times(1)).save(utente);
        verifyNoMoreInteractions(ortoReferenteRepository);
        verifyNoMoreInteractions(utenteRepository);
    }*/
}
