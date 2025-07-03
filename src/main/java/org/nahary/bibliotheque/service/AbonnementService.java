package org.nahary.bibliotheque.service;

import org.nahary.bibliotheque.entity.Abonnement;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AbonnementService {

    List<Abonnement> getAllAbonnements();

    ResponseEntity<Abonnement> getAbonnementById(Long id);

    Abonnement createAbonnement(Abonnement abonnement);

    ResponseEntity<Abonnement> updateAbonnement(Long id, Abonnement updatedAbonnement);

    ResponseEntity<Void> deleteAbonnement(Long id);
}
