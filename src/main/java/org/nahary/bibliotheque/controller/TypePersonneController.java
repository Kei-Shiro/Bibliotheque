package org.nahary.bibliotheque.controller;

import org.nahary.bibliotheque.entity.TypePersonne;
import org.nahary.bibliotheque.service.TypePersonneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/types-personnes")
public class TypePersonneController {

    @Autowired
    private TypePersonneService typePersonneService;

    @GetMapping
    public List<TypePersonne> getAllTypesPersonnes() {
        return typePersonneService.getAllTypesPersonnes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypePersonne> getTypePersonneById(@PathVariable Long id) {
        return typePersonneService.getTypePersonneById(id);
    }

    @PostMapping
    public TypePersonne createTypePersonne(@RequestBody TypePersonne typePersonne) {
        return typePersonneService.createTypePersonne(typePersonne);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TypePersonne> updateTypePersonne(@PathVariable Long id, @RequestBody TypePersonne typePersonne) {
        return typePersonneService.updateTypePersonne(id, typePersonne);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTypePersonne(@PathVariable Long id) {
        return typePersonneService.deleteTypePersonne(id);
    }
}
