document.addEventListener('DOMContentLoaded', () => {
    const typesPersonnesTable = document.getElementById('types-personnes-table').querySelector('tbody');

    // Charger les types de personnes depuis l'API
    fetch('/api/types-personnes')
        .then(response => response.json())
        .then(typesPersonnes => {
            typesPersonnes.forEach(typePersonne => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${typePersonne.id}</td>
                    <td>${typePersonne.nom}</td>
                    <td>${typePersonne.limiteEmprunt}</td>
                    <td>${typePersonne.dureeEmpruntJours}</td>
                    <td>
                        <button class="edit-type-personne" data-id="${typePersonne.id}">Modifier</button>
                        <button class="delete-type-personne" data-id="${typePersonne.id}">Supprimer</button>
                    </td>
                `;
                typesPersonnesTable.appendChild(row);
            });
        });

    // Ajouter un type de personne
    document.getElementById('add-type-personne').addEventListener('click', () => {
        // Logique pour ajouter un type de personne
        alert('Ajouter un type de personne');
    });
});
