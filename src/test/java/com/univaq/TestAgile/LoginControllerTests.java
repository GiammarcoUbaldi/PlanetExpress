package com.univaq.TestAgile;
/*
import com.univaq.TestAgile.controller.api.UtenteController;
import com.univaq.TestAgile.model.Utente;
import com.univaq.TestAgile.repository.UtenteRepository;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;


@SpringBootTest
public class LoginControllerTests {

	@InjectMocks
	private UtenteController utenteController;

	@Mock
	private UtenteRepository utenteRepository;

	@Test
	void contextLoads() {
		System.out.println("TESTSSSSSSSSSSSSSS");
	}

	@Test
	public void testEmailExistsInDatabase() {
		// Given
		String emailUtente = "giovanni.alt1@gmail.com";
		Utente utente = new Utente();
		utente.setEmail(emailUtente);
		utente.setPassword("esempioPassword");
		when(utenteRepository.findByEmail(emailUtente)).thenReturn(utente);

		// When
		Utente trovato = utenteController.getUtenteByEmail(emailUtente);

		// Then
		assertThat(trovato).isNotNull();
		assertThat(trovato.getEmail()).isEqualTo(emailUtente);
	}

	@Test
	public void testInserimentoUtenteNelDatabase() {
		// Given
		String nome = "Mario";
		String cognome = "Rossi";
		String email = "mario.rossi@example.com";
		String password = "password123";
		String tipoUtente = "standard";
		String sesso = "M";
		String indirizzo = "Via Roma 123";
		String nazione = "Italia";
		String numeroTelefono = "1234567890";

		Utente utente = new Utente(nome, cognome, email, password, tipoUtente, sesso, indirizzo, nazione, numeroTelefono);

		when(utenteRepository.save(utente)).thenReturn(utente);

		// When
		Utente utenteInserito = utenteController.registraUtente(utente);

		// Then
		assertThat(utenteInserito).isNotNull();
		assertThat(utenteInserito.getEmail()).isEqualTo(email);

	}
}
*/