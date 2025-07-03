package org.nahary.bibliotheque.controller;

import org.nahary.bibliotheque.entity.Personne;
import org.nahary.bibliotheque.service.PersonneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personnes")
public class PersonneController {

    @Autowired
    private PersonneService personneService;

    @GetMapping
    public List<Personne> getAllPersonnes() {
        return personneService.getAllPersonnes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Personne> getPersonneById(@PathVariable Long id) {
        return personneService.getPersonneById(id);
    }

    @PostMapping
    public Personne createPersonne(@RequestBody Personne personne) {
        return personneService.createPersonne(personne);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Personne> updatePersonne(@PathVariable Long id, @RequestBody Personne personne) {
        return personneService.updatePersonne(id, personne);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersonne(@PathVariable Long id) {
        return personneService.deletePersonne(id);
    }
}
