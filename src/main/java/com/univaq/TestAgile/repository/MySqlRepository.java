package com.univaq.TestAgile.repository;

import com.univaq.TestAgile.model.Pianta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MySqlRepository extends JpaRepository<Pianta,Integer> {

}
