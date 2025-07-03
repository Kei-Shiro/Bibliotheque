package org.nahary.bibliotheque.repository;

import org.nahary.bibliotheque.entity.TypePersonne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypePersonneRepository extends JpaRepository<TypePersonne, Long> {
}
