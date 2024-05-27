package com.univaq.TestAgile.repository;

import com.univaq.TestAgile.model.OrtoReferente;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrtoReferenteRepository extends CrudRepository<OrtoReferente, Long> {
    List<OrtoReferente> findByStato(String stato);

    List<OrtoReferente> findById(String stato);

}
