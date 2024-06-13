package com.univaq.Agile.repository;

import com.univaq.Agile.model.OrtoReferente;
import com.univaq.Agile.model.Utente;
import com.univaq.Agile.model.Zolla;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ZollaRepository extends CrudRepository<Zolla, Long> {

    List<Zolla> findByOrto(OrtoReferente orto);

    List<Zolla> findByOrtoAndUtente(OrtoReferente orto, Utente utente);
}