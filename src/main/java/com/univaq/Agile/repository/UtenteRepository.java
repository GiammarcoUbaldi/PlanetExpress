package com.univaq.Agile.repository;

import com.univaq.Agile.model.Utente;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UtenteRepository extends CrudRepository<Utente, Long> {
    Utente findByNomeAndPassword(String nome, String password);

    List<Utente> findByTipoUtente(String tipo);
    boolean existsByEmail(String email);

    Utente findByEmail(String email);


//    Optional<Utente> findByEmail(String email);

    Utente findByEmailAndPassword(String nome, String password);
}
