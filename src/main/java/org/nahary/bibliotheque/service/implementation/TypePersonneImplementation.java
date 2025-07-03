package org.nahary.bibliotheque.service.implementation;

import org.nahary.bibliotheque.entity.TypePersonne;
import org.nahary.bibliotheque.repository.TypePersonneRepository;
import org.nahary.bibliotheque.service.TypePersonneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypePersonneImplementation implements TypePersonneService {

    @Autowired
    private TypePersonneRepository typePersonneRepository;

    @Override
    public List<TypePersonne> getAllTypesPersonnes() {
        return typePersonneRepository.findAll();
    }

    @Override
    public ResponseEntity<TypePersonne> getTypePersonneById(Long id) {
        Optional<TypePersonne> typePersonne = typePersonneRepository.findById(id);
        return typePersonne.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public TypePersonne createTypePersonne(TypePersonne typePersonne) {
        return typePersonneRepository.save(typePersonne);
    }

    @Override
    public ResponseEntity<TypePersonne> updateTypePersonne(Long id, TypePersonne updatedTypePersonne) {
        Optional<TypePersonne> existingTypePersonne = typePersonneRepository.findById(id);
        if (existingTypePersonne.isPresent()) {
            TypePersonne typePersonne = existingTypePersonne.get();
            typePersonne.setNom(updatedTypePersonne.getNom());
            typePersonne.setLimiteEmprunt(updatedTypePersonne.getLimiteEmprunt());
            typePersonne.setDureeEmpruntJours(updatedTypePersonne.getDureeEmpruntJours());
            return ResponseEntity.ok(typePersonneRepository.save(typePersonne));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Void> deleteTypePersonne(Long id) {
        if (typePersonneRepository.existsById(id)) {
            typePersonneRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
