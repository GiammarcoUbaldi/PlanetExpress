package com.univaq.Agile.repository;

import com.univaq.Agile.model.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {
    List<Post> findByTipo(String tipo);
    List<Post> findAll();

    @Query("SELECT p FROM Post p WHERE p.titolo LIKE %:query% OR p.descrizione LIKE %:query% OR p.username LIKE %:query%")
    List<Post> findByQuery(@Param("query") String query);

}
