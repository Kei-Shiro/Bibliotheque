document.addEventListener('DOMContentLoaded', () => {
    // Charger les réservations depuis l'API
    const reservationsTableBody = document.querySelector('#reservations-table tbody');
    fetch('/api/reservations')
        .then(response => response.json())
        .then(reservations => {
            reservations.forEach(reservation => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${reservation.utilisateur}</td>
                    <td>${reservation.livre}</td>
                    <td>${reservation.dateReservation}</td>
                    <td>${reservation.statut}</td>
                    <td>
                        <button class="edit-btn" data-id="${reservation.id}">Modifier</button>
                        <button class="delete-btn" data-id="${reservation.id}">Supprimer</button>
                    </td>
                `;
                reservationsTableBody.appendChild(row);
            });
        });

    // Gestion du formulaire d'ajout/modification
    const reservationForm = document.querySelector('#reservation-form');
    reservationForm.addEventListener('submit', (event) => {
        event.preventDefault();

        const formData = {
            utilisateur: document.querySelector('#utilisateur').value,
            livre: document.querySelector('#livre').value,
            dateReservation: document.querySelector('#date').value,
            statut: document.querySelector('#statut').value,
        };

        fetch('/api/reservations', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData),
        })
        .then(response => response.json())
        .then(() => {
            alert('Réservation enregistrée avec succès !');
            location.reload();
        });
    });

    // Gestion des boutons Modifier et Supprimer
    reservationsTableBody.addEventListener('click', (event) => {
        const target = event.target;
        const id = target.dataset.id;

        if (target.classList.contains('edit-btn')) {
            // Logique pour modifier une réservation
            alert(`Modifier la réservation avec ID : ${id}`);
        } else if (target.classList.contains('delete-btn')) {
            // Logique pour supprimer une réservation
            fetch(`/api/reservations/${id}`, {
                method: 'DELETE',
            })
            .then(() => {
                alert('Réservation supprimée avec succès !');
                location.reload();
            });
        }
    });
});
