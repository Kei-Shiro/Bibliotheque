package org.nahary.bibliotheque.service;

import org.nahary.bibliotheque.entity.Penalite;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PenaliteService {

    List<Penalite> getAllPenalites();

    ResponseEntity<Penalite> getPenaliteById(Long id);

    Penalite createPenalite(Penalite penalite);

    ResponseEntity<Penalite> updatePenalite(Long id, Penalite updatedPenalite);

    ResponseEntity<Void> deletePenalite(Long id);
}
