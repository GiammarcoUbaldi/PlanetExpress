package com.univaq.TestAgile.repository;

import com.univaq.TestAgile.model.Task;
import com.univaq.TestAgile.model.Utente;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {


}
