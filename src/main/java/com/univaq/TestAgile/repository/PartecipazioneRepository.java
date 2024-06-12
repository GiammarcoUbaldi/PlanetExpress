package com.univaq.TestAgile.repository;

import com.univaq.TestAgile.model.Evento;
import com.univaq.TestAgile.model.Partecipazione;
import com.univaq.TestAgile.model.Utente;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PartecipazioneRepository extends CrudRepository<Partecipazione, Long> {

    List<Partecipazione> findByUtente(Utente utente);

}
