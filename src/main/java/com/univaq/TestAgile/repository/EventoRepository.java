package com.univaq.TestAgile.repository;

import com.univaq.TestAgile.model.Evento;
import org.springframework.data.repository.CrudRepository;

public interface EventoRepository extends CrudRepository<Evento, Long> {
}
