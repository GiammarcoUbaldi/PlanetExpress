package com.univaq.TestAgile.repository;

import com.univaq.TestAgile.model.Commento;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentoRepository extends CrudRepository<Commento, Long> {
    List<Commento> findByTipo(String tipo);
}
