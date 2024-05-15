package com.univaq.TestAgile.repository;

import com.univaq.TestAgile.model.Utente;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
public interface UtenteRepository extends CrudRepository<Utente, Long> {
    Utente findByNomeAndPassword(String nome, String password);

    List<Utente> findByTipoUtente(String tipo);
    boolean existsByEmail(String email);
}
