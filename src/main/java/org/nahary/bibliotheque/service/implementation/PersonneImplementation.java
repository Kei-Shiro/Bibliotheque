package org.nahary.bibliotheque.service.implementation;

import org.nahary.bibliotheque.entity.Personne;
import org.nahary.bibliotheque.repository.PersonneRepository;
import org.nahary.bibliotheque.service.PersonneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonneImplementation implements PersonneService {

    @Autowired
    private PersonneRepository personneRepository;

    @Override
    public List<Personne> getAllPersonnes() {
        return personneRepository.findAll();
    }

    @Override
    public ResponseEntity<Personne> getPersonneById(Long id) {
        Optional<Personne> personne = personneRepository.findById(id);
        return personne.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public Personne createPersonne(Personne personne) {
        return personneRepository.save(personne);
    }

    @Override
    public ResponseEntity<Personne> updatePersonne(Long id, Personne updatedPersonne) {
        Optional<Personne> existingPersonne = personneRepository.findById(id);
        if (existingPersonne.isPresent()) {
            Personne personne = existingPersonne.get();
            personne.setNom(updatedPersonne.getNom());
            personne.setPrenom(updatedPersonne.getPrenom());
            personne.setEmail(updatedPersonne.getEmail());
            personne.setTelephone(updatedPersonne.getTelephone());
            personne.setAdresse(updatedPersonne.getAdresse());
            personne.setDateNaissance(updatedPersonne.getDateNaissance());
            personne.setNumeroCarte(updatedPersonne.getNumeroCarte());
            personne.setTypePersonne(updatedPersonne.getTypePersonne());
            return ResponseEntity.ok(personneRepository.save(personne));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Void> deletePersonne(Long id) {
        if (personneRepository.existsById(id)) {
            personneRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
