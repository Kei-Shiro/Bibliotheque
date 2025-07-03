package org.nahary.bibliotheque.service.implementation;

import org.nahary.bibliotheque.entity.Abonnement;
import org.nahary.bibliotheque.repository.AbonnementRepository;
import org.nahary.bibliotheque.service.AbonnementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AbonnementImplementation implements AbonnementService {

    @Autowired
    private AbonnementRepository abonnementRepository;

    @Override
    public List<Abonnement> getAllAbonnements() {
        return abonnementRepository.findAll();
    }

    @Override
    public ResponseEntity<Abonnement> getAbonnementById(Long id) {
        Optional<Abonnement> abonnement = abonnementRepository.findById(id);
        return abonnement.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public Abonnement createAbonnement(Abonnement abonnement) {
        return abonnementRepository.save(abonnement);
    }

    @Override
    public ResponseEntity<Abonnement> updateAbonnement(Long id, Abonnement updatedAbonnement) {
        Optional<Abonnement> existingAbonnement = abonnementRepository.findById(id);
        if (existingAbonnement.isPresent()) {
            Abonnement abonnement = existingAbonnement.get();
            abonnement.setDateDebut(updatedAbonnement.getDateDebut());
            abonnement.setDateFin(updatedAbonnement.getDateFin());
            abonnement.setStatut(updatedAbonnement.getStatut());
            abonnement.setTypeAbonnement(updatedAbonnement.getTypeAbonnement());
            return ResponseEntity.ok(abonnementRepository.save(abonnement));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Void> deleteAbonnement(Long id) {
        if (abonnementRepository.existsById(id)) {
            abonnementRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
