package com.univaq.Agile.repository;

import com.univaq.Agile.model.Commento;
import com.univaq.Agile.model.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentoRepository extends CrudRepository<Commento, Long> {

    List<Commento> findByPost(Post post);

}
