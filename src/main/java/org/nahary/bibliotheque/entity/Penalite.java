package org.nahary.bibliotheque.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "penalite")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Penalite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "personne_id", nullable = false)
    private Personne personne;

    @ManyToOne
    @JoinColumn(name = "emprunt_id")
    private Emprunt emprunt;

    @Column(name = "type_penalite")
    @Enumerated(EnumType.STRING)
    private TypePenalite typePenalite;

    @Column(name = "statut")
    @Enumerated(EnumType.STRING)
    private Statut statut;

    @Column(name = "date_creation", nullable = false)
    private LocalDateTime dateCreation;

    @Column(name = "date_debut_penalite")
    private LocalDate dateDebutPenalite;

    @Column(name = "date_fin_penalite")
    private LocalDate dateFinPenalite;

    @Column(name = "jours_suspension")
    private Integer joursSuspension;

    @Column(name = "nombre_avertissements")
    private Integer nombreAvertissements;

    @Column(name = "description")
    private String description;

    @Column(name = "motif")
    private String motif;

    @Column(name = "appliquee_par")
    private String appliqueePar;

    public enum TypePenalite {
        RETARD, DETERIORATION, PERTE, SUSPENSION_EMPRUNT, AVERTISSEMENT, AUTRE
    }

    public enum Statut {
        ACTIVE, TERMINEE, ANNULEE
    }
}
