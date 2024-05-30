package com.univaq.TestAgile.repository;

import com.univaq.TestAgile.model.Commento;
import com.univaq.TestAgile.model.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentoRepository extends CrudRepository<Commento, Long> {

    List<Commento> findByPost(Post post);

}
