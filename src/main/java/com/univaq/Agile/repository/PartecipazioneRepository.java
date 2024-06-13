package com.univaq.Agile.repository;

import com.univaq.Agile.model.Partecipazione;
import com.univaq.Agile.model.Utente;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PartecipazioneRepository extends CrudRepository<Partecipazione, Long> {

    List<Partecipazione> findByUtente(Utente utente);

        boolean existsByEventoIdAndUtenteId(Long eventoId, Long utenteId);

}
