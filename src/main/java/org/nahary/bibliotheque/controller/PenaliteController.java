package org.nahary.bibliotheque.controller;

import org.nahary.bibliotheque.entity.Penalite;
import org.nahary.bibliotheque.service.PenaliteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/penalites")
public class PenaliteController {

    @Autowired
    private PenaliteService penaliteService;

    @GetMapping
    public List<Penalite> getAllPenalites() {
        return penaliteService.getAllPenalites();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Penalite> getPenaliteById(@PathVariable Long id) {
        return penaliteService.getPenaliteById(id);
    }

    @PostMapping
    public Penalite createPenalite(@RequestBody Penalite penalite) {
        return penaliteService.createPenalite(penalite);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Penalite> updatePenalite(@PathVariable Long id, @RequestBody Penalite penalite) {
        return penaliteService.updatePenalite(id, penalite);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePenalite(@PathVariable Long id) {
        return penaliteService.deletePenalite(id);
    }
}
