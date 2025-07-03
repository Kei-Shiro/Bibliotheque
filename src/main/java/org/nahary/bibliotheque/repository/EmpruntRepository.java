package org.nahary.bibliotheque.repository;

import org.nahary.bibliotheque.entity.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {

    long countByPersonneIdAndStatut(Long personneId, Emprunt.Statut statut);
}
