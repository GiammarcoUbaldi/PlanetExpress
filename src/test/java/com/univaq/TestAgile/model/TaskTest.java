package com.univaq.TestAgile.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    public void testTaskConstructorAndGetters() {
        String cosaDaFare = "Finish homework";
        Integer eseguita = 1;

        Task task = new Task(cosaDaFare, eseguita);

        assertEquals(cosaDaFare, task.getCosaDaFare());
        assertEquals(eseguita, task.getCheck());
    }

    @Test
    public void testDefaultConstructor() {
        Task task = new Task();
        assertNull(task.getId());
        assertNull(task.getCosaDaFare());
        assertNull(task.getCheck());
        assertNull(task.getUtente());
    }

    @Test
    public void testSettersAndGetters() {
        Task task = new Task();
        Long id = 1L;
        String cosaDaFare = "Go shopping";
        Integer eseguita = 0;
        Utente utente = new Utente();

        task.setId(id);
        task.setCosaDaFare(cosaDaFare);
        task.setCheck(eseguita);
        task.setUtente(utente);

        assertEquals(id, task.getId());
        assertEquals(cosaDaFare, task.getCosaDaFare());
        assertEquals(eseguita, task.getCheck());
        assertEquals(utente, task.getUtente());
    }

    @Test
    public void testToString() {
        Task task = new Task();
        Long id = 1L;
        String cosaDaFare = "Go running";
        Integer eseguita = 1;
        Utente utente = new Utente();

        task.setId(id);
        task.setCosaDaFare(cosaDaFare);
        task.setCheck(eseguita);
        task.setUtente(utente);

        String expectedString = "Task{id=1, cosaDaFare='Go running', eseguita=1, utente=" + utente + "}";
        assertEquals(expectedString, task.toString());
    }
}
