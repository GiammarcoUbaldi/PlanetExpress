package com.univaq.TestAgile.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ZollaTest {

    @InjectMocks
    private Zolla zolla;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetGiorniPossesso() {
        LocalDate dataPrenotazione = LocalDate.of(2023, 5, 1);
        Date dataPrenotazioneDate = Date.from(dataPrenotazione.atStartOfDay(ZoneId.systemDefault()).toInstant());

        zolla = new Zolla();
        zolla.setDataPrenotazione(dataPrenotazioneDate);
        LocalDate dataOdierna = LocalDate.now();
        long giorniAttesi = ChronoUnit.DAYS.between(dataPrenotazione, dataOdierna);
        assertEquals(String.valueOf(giorniAttesi), zolla.getGiorniPossesso());
    }
}

