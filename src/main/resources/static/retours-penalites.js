document.addEventListener('DOMContentLoaded', () => {
    const retoursTable = document.getElementById('retours-table').querySelector('tbody');
    const penalitesTable = document.getElementById('penalites-table').querySelector('tbody');

    // Charger les retours depuis l'API
    fetch('/api/emprunts?statut=RETOURNE')
        .then(response => response.json())
        .then(retours => {
            retours.forEach(retour => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${retour.id}</td>
                    <td>${retour.personne.nom} ${retour.personne.prenom}</td>
                    <td>${retour.livre.titre}</td>
                    <td>${retour.dateRetourEffective}</td>
                    <td>
                        <button class="view-retour" data-id="${retour.id}">Voir</button>
                    </td>
                `;
                retoursTable.appendChild(row);
            });
        });

    // Charger les pénalités depuis l'API
    fetch('/api/penalites')
        .then(response => response.json())
        .then(penalites => {
            penalites.forEach(penalite => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${penalite.id}</td>
                    <td>${penalite.personne.nom} ${penalite.personne.prenom}</td>
                    <td>${penalite.typePenalite}</td>
                    <td>${penalite.montant}</td>
                    <td>${penalite.statut}</td>
                    <td>
                        <button class="view-penalite" data-id="${penalite.id}">Voir</button>
                    </td>
                `;
                penalitesTable.appendChild(row);
            });
        });
});
