package org.nahary.bibliotheque.service.implementation;

import org.nahary.bibliotheque.entity.Livre;
import org.nahary.bibliotheque.repository.LivreRepository;
import org.nahary.bibliotheque.service.LivreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivreImplementation implements LivreService {

    @Autowired
    private LivreRepository livreRepository;

    @Override
    public List<Livre> getAllLivres() {
        return livreRepository.findAll();
    }

    @Override
    public ResponseEntity<Livre> getLivreById(Long id) {
        Optional<Livre> livre = livreRepository.findById(id);
        return livre.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public Livre createLivre(Livre livre) {
        return livreRepository.save(livre);
    }

    @Override
    public ResponseEntity<Livre> updateLivre(Long id, Livre updatedLivre) {
        Optional<Livre> existingLivre = livreRepository.findById(id);
        if (existingLivre.isPresent()) {
            Livre livre = existingLivre.get();
            livre.setTitre(updatedLivre.getTitre());
            livre.setAuteur(updatedLivre.getAuteur());
            livre.setIsbn(updatedLivre.getIsbn());
            livre.setEditeur(updatedLivre.getEditeur());
            livre.setAnneePublication(updatedLivre.getAnneePublication());
            livre.setGenre(updatedLivre.getGenre());
            livre.setNombrePages(updatedLivre.getNombrePages());
            livre.setNombreExemplaires(updatedLivre.getNombreExemplaires());
            livre.setExemplairesDisponibles(updatedLivre.getExemplairesDisponibles());
            livre.setDateAcquisition(updatedLivre.getDateAcquisition());
            livre.setStatut(updatedLivre.getStatut());
            return ResponseEntity.ok(livreRepository.save(livre));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Void> deleteLivre(Long id) {
        if (livreRepository.existsById(id)) {
            livreRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
