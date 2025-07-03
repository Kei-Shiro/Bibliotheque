document.addEventListener('DOMContentLoaded', () => {
    const empruntsTable = document.getElementById('emprunts-table').querySelector('tbody');

    // Charger les emprunts depuis l'API
    fetch('/api/emprunts')
        .then(response => response.json())
        .then(emprunts => {
            emprunts.forEach(emprunt => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${emprunt.id}</td>
                    <td>${emprunt.personne.nom} ${emprunt.personne.prenom}</td>
                    <td>${emprunt.livre.titre}</td>
                    <td>${emprunt.dateEmprunt}</td>
                    <td>${emprunt.dateRetourPrevue}</td>
                    <td>${emprunt.statut}</td>
                    <td>
                        <button class="edit-emprunt" data-id="${emprunt.id}">Modifier</button>
                        <button class="delete-emprunt" data-id="${emprunt.id}">Supprimer</button>
                    </td>
                `;
                empruntsTable.appendChild(row);
            });
        });

    // Ajouter un emprunt
    document.getElementById('add-emprunt').addEventListener('click', () => {
        // Logique pour ajouter un emprunt
        alert('Ajouter un emprunt');
    });
});
