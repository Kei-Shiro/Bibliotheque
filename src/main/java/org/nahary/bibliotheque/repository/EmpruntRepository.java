package org.nahary.bibliotheque.repository;

import org.nahary.bibliotheque.entity.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {

    long countByPersonneIdAndStatut(Long personneId, Emprunt.Statut statut);

    List<Emprunt> findByStatut(Emprunt.Statut statut);

    List<Emprunt> findByPersonneNomContainingIgnoreCase(String nom);

    List<Emprunt> findByPersonneId(Long personneId);
}
