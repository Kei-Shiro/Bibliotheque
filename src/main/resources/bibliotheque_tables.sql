-- Script de création des tables pour la base de données Bibliothèque
-- Base de données MySQL

-- Création de la base de données
DROP DATABASE IF EXISTS bibliotheque_db;
CREATE DATABASE IF NOT EXISTS bibliotheque_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE bibliotheque_db;

-- Table des types de personnes (Étudiant, Professeur, etc.)
CREATE TABLE type_personne (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL UNIQUE,
    limite_emprunt INT NOT NULL,
    duree_emprunt_jours INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Table des personnes
CREATE TABLE personne (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    telephone VARCHAR(20),
    adresse TEXT,
    date_naissance DATE,
    numero_carte VARCHAR(20) NOT NULL UNIQUE,
    type_personne_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (type_personne_id) REFERENCES type_personne(id)
);

-- Table des abonnements
CREATE TABLE abonnement (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    personne_id BIGINT NOT NULL,
    date_debut DATE NOT NULL,
    date_fin DATE NOT NULL,
    statut ENUM('ACTIF', 'EXPIRE', 'SUSPENDU', 'ANNULE') DEFAULT 'ACTIF',
    type_abonnement VARCHAR(50) DEFAULT 'STANDARD',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (personne_id) REFERENCES personne(id),
    UNIQUE KEY unique_personne_abonnement (personne_id)
);

-- Table des livres
CREATE TABLE livre (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titre VARCHAR(255) NOT NULL,
    auteur VARCHAR(200) NOT NULL,
    isbn VARCHAR(20) UNIQUE,
    editeur VARCHAR(150),
    annee_publication INT,
    genre VARCHAR(100),
    nombre_pages INT,
    nombre_exemplaires INT NOT NULL,
    exemplaires_disponibles INT NOT NULL,
    date_acquisition DATE,
    statut ENUM('DISPONIBLE', 'EMPRUNTE', 'RESERVE', 'ENDOMMAGE', 'PERDU') DEFAULT 'DISPONIBLE',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Table des emprunts
CREATE TABLE emprunt (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    personne_id BIGINT NOT NULL,
    livre_id BIGINT NOT NULL,
    date_emprunt DATE NOT NULL,
    date_retour_prevue DATE NOT NULL,
    date_retour_effective DATE,
    statut ENUM('EN_COURS', 'RETOURNE', 'EN_RETARD', 'PERDU') DEFAULT 'EN_COURS',
    nombre_prolongements INT DEFAULT 0,
    nombre_max_prolongements INT DEFAULT 2,
    commentaires TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (personne_id) REFERENCES personne(id),
    FOREIGN KEY (livre_id) REFERENCES livre(id)
);

-- Table des réservations
CREATE TABLE reservation (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    personne_id BIGINT NOT NULL,
    livre_id BIGINT NOT NULL,
    date_reservation DATETIME NOT NULL,
    date_expiration DATE NOT NULL,
    statut ENUM('ACTIVE', 'SATISFAITE', 'EXPIREE', 'ANNULEE') DEFAULT 'ACTIVE',
    date_notification DATETIME,
    priorite INT DEFAULT 1,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (personne_id) REFERENCES personne(id),
    FOREIGN KEY (livre_id) REFERENCES livre(id)
);

-- Table des prolongements
CREATE TABLE prolongement (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    emprunt_id BIGINT NOT NULL,
    date_prolongement DATETIME NOT NULL,
    ancienne_date_retour DATE NOT NULL,
    nouvelle_date_retour DATE NOT NULL,
    duree_prolongement_jours INT NOT NULL,
    motif TEXT,
    approuve_par VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (emprunt_id) REFERENCES emprunt(id)
);

-- Table des pénalités (non-monétaires)
CREATE TABLE penalite (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    personne_id BIGINT NOT NULL,
    emprunt_id BIGINT,
    type_penalite ENUM('RETARD', 'DETERIORATION', 'PERTE', 'SUSPENSION_EMPRUNT', 'AVERTISSEMENT', 'AUTRE') NOT NULL,
    statut ENUM('ACTIVE', 'TERMINEE', 'ANNULEE') DEFAULT 'ACTIVE',
    date_creation DATETIME NOT NULL,
    date_debut_penalite DATE,
    date_fin_penalite DATE,
    jours_suspension INT DEFAULT 0,
    nombre_avertissements INT DEFAULT 0,
    description TEXT,
    motif TEXT,
    appliquee_par VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (personne_id) REFERENCES personne(id),
    FOREIGN KEY (emprunt_id) REFERENCES emprunt(id)
);

-- Table pour suivre l'historique des suspensions
CREATE TABLE suspension_emprunt (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    personne_id BIGINT NOT NULL,
    penalite_id BIGINT NOT NULL,
    date_debut DATE NOT NULL,
    date_fin DATE NOT NULL,
    motif TEXT,
    statut ENUM('ACTIVE', 'TERMINEE', 'ANNULEE') DEFAULT 'ACTIVE',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (personne_id) REFERENCES personne(id),
    FOREIGN KEY (penalite_id) REFERENCES penalite(id)
);

-- Table des logs d'audit
CREATE TABLE audit_log (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    action_type VARCHAR(100) NOT NULL,
    description TEXT NOT NULL,
    timestamp DATETIME NOT NULL,
    user_id BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Table des rôles
CREATE TABLE role (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

-- Table des utilisateurs
CREATE TABLE user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

-- Table de liaison entre utilisateurs et rôles
CREATE TABLE user_roles (
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (role_id) REFERENCES role(id)
);

-- Index pour optimiser les performances
CREATE INDEX idx_personne_email ON personne(email);
CREATE INDEX idx_personne_numero_carte ON personne(numero_carte);
CREATE INDEX idx_livre_titre ON livre(titre);
CREATE INDEX idx_livre_auteur ON livre(auteur);
CREATE INDEX idx_livre_isbn ON livre(isbn);
CREATE INDEX idx_emprunt_personne ON emprunt(personne_id);
CREATE INDEX idx_emprunt_livre ON emprunt(livre_id);
CREATE INDEX idx_emprunt_date_retour ON emprunt(date_retour_prevue);
CREATE INDEX idx_emprunt_statut ON emprunt(statut);
CREATE INDEX idx_reservation_personne ON reservation(personne_id);
CREATE INDEX idx_reservation_livre ON reservation(livre_id);
CREATE INDEX idx_reservation_statut ON reservation(statut);
CREATE INDEX idx_penalite_personne ON penalite(personne_id);
CREATE INDEX idx_penalite_statut ON penalite(statut);
CREATE INDEX idx_abonnement_personne ON abonnement(personne_id);
CREATE INDEX idx_abonnement_statut ON abonnement(statut);
