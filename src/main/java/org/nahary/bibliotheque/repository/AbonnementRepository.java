package org.nahary.bibliotheque.repository;

import org.nahary.bibliotheque.entity.Abonnement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbonnementRepository extends JpaRepository<Abonnement, Long> {
}
