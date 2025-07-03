package org.nahary.bibliotheque.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "type_personne")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypePersonne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom", nullable = false, unique = true)
    private String nom;

    @Column(name = "limite_emprunt", nullable = false)
    private Integer limiteEmprunt;

    @Column(name = "duree_emprunt_jours", nullable = false)
    private Integer dureeEmpruntJours;
}
