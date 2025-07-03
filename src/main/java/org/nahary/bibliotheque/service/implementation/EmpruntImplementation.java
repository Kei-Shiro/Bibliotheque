package org.nahary.bibliotheque.service.implementation;

import org.nahary.bibliotheque.entity.Emprunt;
import org.nahary.bibliotheque.repository.EmpruntRepository;
import org.nahary.bibliotheque.service.EmpruntService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EmpruntImplementation implements EmpruntService {

    @Autowired
    private EmpruntRepository empruntRepository;

    @Override
    public List<Emprunt> getAllEmprunts() {
        return empruntRepository.findAll();
    }

    @Override
    public ResponseEntity<Emprunt> getEmpruntById(Long id) {
        Optional<Emprunt> emprunt = empruntRepository.findById(id);
        return emprunt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public Emprunt createEmprunt(Emprunt emprunt) {
        // Vérifier si le livre est disponible
        if (emprunt.getLivre().getExemplairesDisponibles() <= 0) {
            throw new IllegalStateException("Le livre n'est pas disponible pour l'emprunt.");
        }

        // Vérifier si la personne n'a pas atteint sa limite d'emprunts
        long nombreEmpruntsActifs = empruntRepository.countByPersonneIdAndStatut(emprunt.getPersonne().getId(), Emprunt.Statut.EN_COURS);
        if (nombreEmpruntsActifs >= emprunt.getPersonne().getTypePersonne().getLimiteEmprunt()) {
            throw new IllegalStateException("La limite d'emprunts pour cette personne a été atteinte.");
        }

        // Vérifier si la durée maximale d'emprunt est respectée
        int dureeMaximale = emprunt.getPersonne().getTypePersonne().getDureeEmpruntJours();
        if (emprunt.getDateRetourPrevue().isAfter(emprunt.getDateEmprunt().plusDays(dureeMaximale))) {
            throw new IllegalStateException("La durée maximale d'emprunt pour cette personne a été dépassée.");
        }

        // Réduire le nombre d'exemplaires disponibles du livre
        emprunt.getLivre().setExemplairesDisponibles(emprunt.getLivre().getExemplairesDisponibles() - 1);

        // Sauvegarder l'emprunt
        return empruntRepository.save(emprunt);
    }

    @Override
    public ResponseEntity<Emprunt> updateEmprunt(Long id, Emprunt updatedEmprunt) {
        Optional<Emprunt> existingEmprunt = empruntRepository.findById(id);
        if (existingEmprunt.isPresent()) {
            Emprunt emprunt = existingEmprunt.get();
            emprunt.setDateEmprunt(updatedEmprunt.getDateEmprunt());
            emprunt.setDateRetourPrevue(updatedEmprunt.getDateRetourPrevue());
            emprunt.setDateRetourEffective(updatedEmprunt.getDateRetourEffective());
            emprunt.setStatut(updatedEmprunt.getStatut());
            emprunt.setNombreProlongements(updatedEmprunt.getNombreProlongements());
            emprunt.setCommentaires(updatedEmprunt.getCommentaires());
            return ResponseEntity.ok(empruntRepository.save(emprunt));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Void> deleteEmprunt(Long id) {
        if (empruntRepository.existsById(id)) {
            empruntRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public void verifierRetards() {
        List<Emprunt> empruntsEnCours = empruntRepository.findByStatut(Emprunt.Statut.EN_COURS);
        for (Emprunt emprunt : empruntsEnCours) {
            if (emprunt.getDateRetourPrevue().isBefore(LocalDate.now())) {
                emprunt.setStatut(Emprunt.Statut.EN_RETARD);
                empruntRepository.save(emprunt);
            }
        }
    }

    public ResponseEntity<Emprunt> retournerLivre(Long empruntId) {
        Optional<Emprunt> empruntOptional = empruntRepository.findById(empruntId);
        if (empruntOptional.isPresent()) {
            Emprunt emprunt = empruntOptional.get();

            // Vérifier si l'emprunt est en cours
            if (!emprunt.getStatut().equals(Emprunt.Statut.EN_COURS)) {
                throw new IllegalStateException("Le livre n'est pas actuellement emprunté.");
            }

            // Enregistrer la date de retour
            emprunt.setDateRetourEffective(LocalDate.now());

            // Mettre à jour le statut de l'emprunt
            emprunt.setStatut(Emprunt.Statut.RETOURNE);

            // Augmenter le nombre d'exemplaires disponibles du livre
            emprunt.getLivre().setExemplairesDisponibles(emprunt.getLivre().getExemplairesDisponibles() + 1);

            // Sauvegarder les modifications
            empruntRepository.save(emprunt);

            return ResponseEntity.ok(emprunt);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
