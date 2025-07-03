package org.nahary.bibliotheque.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "abonnement")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Abonnement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "personne_id", nullable = false)
    private Personne personne;

    @Column(name = "date_debut", nullable = false)
    private LocalDate dateDebut;

    @Column(name = "date_fin", nullable = false)
    private LocalDate dateFin;

    @Column(name = "statut")
    @Enumerated(EnumType.STRING)
    private Statut statut;

    @Column(name = "type_abonnement")
    private String typeAbonnement;

    public enum Statut {
        ACTIF, EXPIRE, SUSPENDU, ANNULE
    }
}
