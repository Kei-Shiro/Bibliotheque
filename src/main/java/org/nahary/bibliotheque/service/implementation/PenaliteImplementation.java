package org.nahary.bibliotheque.service.implementation;

import org.nahary.bibliotheque.entity.Penalite;
import org.nahary.bibliotheque.repository.PenaliteRepository;
import org.nahary.bibliotheque.service.PenaliteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PenaliteImplementation implements PenaliteService {

    @Autowired
    private PenaliteRepository penaliteRepository;

    @Override
    public List<Penalite> getAllPenalites() {
        return penaliteRepository.findAll();
    }

    @Override
    public ResponseEntity<Penalite> getPenaliteById(Long id) {
        Optional<Penalite> penalite = penaliteRepository.findById(id);
        return penalite.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public Penalite createPenalite(Penalite penalite) {
        return penaliteRepository.save(penalite);
    }

    @Override
    public ResponseEntity<Penalite> updatePenalite(Long id, Penalite updatedPenalite) {
        Optional<Penalite> existingPenalite = penaliteRepository.findById(id);
        if (existingPenalite.isPresent()) {
            Penalite penalite = existingPenalite.get();
            penalite.setDateCreation(updatedPenalite.getDateCreation());
            penalite.setDateDebutPenalite(updatedPenalite.getDateDebutPenalite());
            penalite.setDateFinPenalite(updatedPenalite.getDateFinPenalite());
            penalite.setJoursSuspension(updatedPenalite.getJoursSuspension());
            penalite.setNombreAvertissements(updatedPenalite.getNombreAvertissements());
            penalite.setDescription(updatedPenalite.getDescription());
            penalite.setMotif(updatedPenalite.getMotif());
            penalite.setAppliqueePar(updatedPenalite.getAppliqueePar());
            penalite.setStatut(updatedPenalite.getStatut());
            penalite.setTypePenalite(updatedPenalite.getTypePenalite());
            return ResponseEntity.ok(penaliteRepository.save(penalite));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Void> deletePenalite(Long id) {
        if (penaliteRepository.existsById(id)) {
            penaliteRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
