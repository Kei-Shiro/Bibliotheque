package org.nahary.bibliotheque.service;

import org.nahary.bibliotheque.entity.Emprunt;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmpruntService {

    List<Emprunt> getAllEmprunts();

    ResponseEntity<Emprunt> getEmpruntById(Long id);

    Emprunt createEmprunt(Emprunt emprunt);

    ResponseEntity<Emprunt> updateEmprunt(Long id, Emprunt updatedEmprunt);

    ResponseEntity<Void> deleteEmprunt(Long id);
}
