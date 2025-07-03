package org.nahary.bibliotheque.service;

import org.nahary.bibliotheque.entity.Personne;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PersonneService {

    List<Personne> getAllPersonnes();

    ResponseEntity<Personne> getPersonneById(Long id);

    Personne createPersonne(Personne personne);

    ResponseEntity<Personne> updatePersonne(Long id, Personne updatedPersonne);

    ResponseEntity<Void> deletePersonne(Long id);
}
