document.addEventListener('DOMContentLoaded', () => {
    // Fonction pour insérer les données dans le tableau des emprunts
    const empruntsTableBody = document.querySelector('#emprunts-statistics-table tbody');
    fetch('/api/emprunts')
        .then(response => response.json())
        .then(empruntsData => {
            empruntsData.forEach(emprunt => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${emprunt.utilisateur}</td>
                    <td>${emprunt.livresEmpruntes}</td>
                    <td>${emprunt.empruntsRetournes}</td>
                `;
                empruntsTableBody.appendChild(row);
            });
        });

    // Fonction pour insérer les données dans le tableau des pénalités
    const penalitesTableBody = document.querySelector('#penalites-reports-table tbody');
    fetch('/api/penalites')
        .then(response => response.json())
        .then(penalitesData => {
            penalitesData.forEach(penalite => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${penalite.utilisateur}</td>
                    <td>${penalite.typePenalite}</td>
                    <td>${penalite.montantTotal}</td>
                    <td>${penalite.statut}</td>
                `;
                penalitesTableBody.appendChild(row);
            });
        });
});
