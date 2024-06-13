package com.univaq.Agile.repository;

import com.univaq.Agile.model.OrtoReferente;
import com.univaq.Agile.model.Utente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrtoReferenteRepository extends CrudRepository<OrtoReferente, Long> {
    List<OrtoReferente> findByStato(String stato);

    List<OrtoReferente> findByUtente(Utente utente);

    // Metodo per trovare tramite l'ID dell'utente
    List<OrtoReferente> findByUtente_Id(Long idRef);

    // Query personalizzata per trovare OrtoReferente tramite ID utente e ID orto
    @Query("SELECT o FROM OrtoReferente o WHERE o.id = :idOrto AND o.utente = :utente")
    OrtoReferente findOrtoReferenteByUtenteAndId(@Param("utente") Utente utente, @Param("idOrto") Long idOrto);

}
