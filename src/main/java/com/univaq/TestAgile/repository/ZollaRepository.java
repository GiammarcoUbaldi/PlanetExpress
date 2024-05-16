package com.univaq.TestAgile.repository;

import com.univaq.TestAgile.model.OrtoReferente;
import com.univaq.TestAgile.model.Zolla;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ZollaRepository extends CrudRepository<Zolla, Long> {

    List<Zolla> findByOrto(OrtoReferente orto);
}