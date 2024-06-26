package com.univaq.TestAgile.repository;

import com.univaq.TestAgile.model.Evento;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EventoRepository extends CrudRepository<Evento, Long> {

    List<Evento> findByAccettato(String accettato);
   // List<Evento> findByEventiRef(Long idRef);

    List<Evento> findByLuogo(String citta);

    List<Evento> findByIdReferente(long id);
}
