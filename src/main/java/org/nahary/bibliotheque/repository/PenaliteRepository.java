package org.nahary.bibliotheque.repository;

import org.nahary.bibliotheque.entity.Penalite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PenaliteRepository extends JpaRepository<Penalite, Long> {
}
