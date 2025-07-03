document.addEventListener('DOMContentLoaded', () => {
    // Charger les abonnements depuis l'API
    const abonnementsTableBody = document.querySelector('#abonnements-table tbody');
    fetch('/api/abonnements')
        .then(response => response.json())
        .then(abonnements => {
            abonnements.forEach(abonnement => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${abonnement.utilisateur}</td>
                    <td>${abonnement.type}</td>
                    <td>${abonnement.dateDebut}</td>
                    <td>${abonnement.dateFin}</td>
                    <td>
                        <button class="edit-btn" data-id="${abonnement.id}">Modifier</button>
                        <button class="delete-btn" data-id="${abonnement.id}">Supprimer</button>
                    </td>
                `;
                abonnementsTableBody.appendChild(row);
            });
        });

    // Gestion du formulaire d'ajout/modification
    const abonnementForm = document.querySelector('#abonnement-form');
    abonnementForm.addEventListener('submit', (event) => {
        event.preventDefault();

        const formData = {
            utilisateur: document.querySelector('#utilisateur').value,
            type: document.querySelector('#type').value,
            dateDebut: document.querySelector('#date-debut').value,
            dateFin: document.querySelector('#date-fin').value,
        };

        fetch('/api/abonnements', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData),
        })
        .then(response => response.json())
        .then(() => {
            alert('Abonnement enregistré avec succès !');
            location.reload();
        });
    });

    // Gestion des boutons Modifier et Supprimer
    abonnementsTableBody.addEventListener('click', (event) => {
        const target = event.target;
        const id = target.dataset.id;

        if (target.classList.contains('edit-btn')) {
            // Logique pour modifier un abonnement
            alert(`Modifier l'abonnement avec ID : ${id}`);
        } else if (target.classList.contains('delete-btn')) {
            // Logique pour supprimer un abonnement
            fetch(`/api/abonnements/${id}`, {
                method: 'DELETE',
            })
            .then(() => {
                alert('Abonnement supprimé avec succès !');
                location.reload();
            });
        }
    });
});
