package org.nahary.bibliotheque.service;

import org.nahary.bibliotheque.entity.TypePersonne;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TypePersonneService {

    List<TypePersonne> getAllTypesPersonnes();

    ResponseEntity<TypePersonne> getTypePersonneById(Long id);

    TypePersonne createTypePersonne(TypePersonne typePersonne);

    ResponseEntity<TypePersonne> updateTypePersonne(Long id, TypePersonne updatedTypePersonne);

    ResponseEntity<Void> deleteTypePersonne(Long id);
}
