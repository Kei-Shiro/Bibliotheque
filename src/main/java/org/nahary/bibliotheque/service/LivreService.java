package org.nahary.bibliotheque.service;

import org.nahary.bibliotheque.entity.Livre;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LivreService {

    List<Livre> getAllLivres();

    ResponseEntity<Livre> getLivreById(Long id);

    Livre createLivre(Livre livre);

    ResponseEntity<Livre> updateLivre(Long id, Livre updatedLivre);

    ResponseEntity<Void> deleteLivre(Long id);
}
