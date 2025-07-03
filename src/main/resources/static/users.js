document.addEventListener('DOMContentLoaded', () => {
    const usersTable = document.getElementById('users-table').querySelector('tbody');

    // Charger les utilisateurs depuis l'API
    fetch('/api/users')
        .then(response => response.json())
        .then(users => {
            users.forEach(user => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${user.id}</td>
                    <td>${user.username}</td>
                    <td>${user.roles.map(role => role.name).join(', ')}</td>
                    <td>
                        <button class="edit-user" data-id="${user.id}">Modifier</button>
                        <button class="delete-user" data-id="${user.id}">Supprimer</button>
                    </td>
                `;
                usersTable.appendChild(row);
            });
        });

    // Ajouter un utilisateur
    document.getElementById('add-user').addEventListener('click', () => {
        // Logique pour ajouter un utilisateur
        alert('Ajouter un utilisateur');
    });
});
