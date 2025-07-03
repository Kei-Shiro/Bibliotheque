package org.nahary.bibliotheque.controller;

import org.nahary.bibliotheque.entity.Abonnement;
import org.nahary.bibliotheque.service.AbonnementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/abonnements")
public class AbonnementController {

    @Autowired
    private AbonnementService abonnementService;

    @GetMapping
    public List<Abonnement> getAllAbonnements() {
        return abonnementService.getAllAbonnements();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Abonnement> getAbonnementById(@PathVariable Long id) {
        return abonnementService.getAbonnementById(id);
    }

    @PostMapping
    public Abonnement createAbonnement(@RequestBody Abonnement abonnement) {
        return abonnementService.createAbonnement(abonnement);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Abonnement> updateAbonnement(@PathVariable Long id, @RequestBody Abonnement abonnement) {
        return abonnementService.updateAbonnement(id, abonnement);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAbonnement(@PathVariable Long id) {
        return abonnementService.deleteAbonnement(id);
    }
}
