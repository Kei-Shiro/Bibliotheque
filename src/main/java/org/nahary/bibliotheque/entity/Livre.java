package org.nahary.bibliotheque.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "livre")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Livre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titre", nullable = false)
    private String titre;

    @Column(name = "auteur", nullable = false)
    private String auteur;

    @Column(name = "isbn", unique = true)
    private String isbn;

    @Column(name = "editeur")
    private String editeur;

    @Column(name = "annee_publication")
    private Integer anneePublication;

    @Column(name = "genre")
    private String genre;

    @Column(name = "nombre_pages")
    private Integer nombrePages;

    @Column(name = "nombre_exemplaires", nullable = false)
    private Integer nombreExemplaires;

    @Column(name = "exemplaires_disponibles", nullable = false)
    private Integer exemplairesDisponibles;

    @Column(name = "date_acquisition")
    private LocalDate dateAcquisition;

    @Column(name = "statut")
    @Enumerated(EnumType.STRING)
    private Statut statut;

    public enum Statut {
        DISPONIBLE, EMPRUNTE, RESERVE, ENDOMMAGE, PERDU
    }
}
