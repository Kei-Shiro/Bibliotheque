package org.nahary.bibliotheque.repository;

import org.nahary.bibliotheque.entity.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivreRepository extends JpaRepository<Livre, Long> {

    List<Livre> findByTitreContainingIgnoreCase(String titre);

    List<Livre> findByAuteurContainingIgnoreCase(String auteur);
}
