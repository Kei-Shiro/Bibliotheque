document.addEventListener('DOMContentLoaded', () => {
    const searchInput = document.getElementById('search-input');
    const searchButton = document.getElementById('search-button');
    const resultsTable = document.getElementById('results-table').querySelector('tbody');

    // Fonction pour effectuer une recherche
    const performSearch = () => {
        const query = searchInput.value;
        fetch(`/api/search?query=${encodeURIComponent(query)}`)
            .then(response => response.json())
            .then(results => {
                resultsTable.innerHTML = '';
                results.forEach(result => {
                    const row = document.createElement('tr');
                    row.innerHTML = `
                        <td>${result.id}</td>
                        <td>${result.name}</td>
                        <td>${result.type}</td>
                        <td>
                            <button class="view-result" data-id="${result.id}">Voir</button>
                        </td>
                    `;
                    resultsTable.appendChild(row);
                });
            });
    };

    // Ajouter un écouteur d'événement au bouton de recherche
    searchButton.addEventListener('click', performSearch);

    // Ajouter un écouteur d'événement pour la recherche en temps réel
    searchInput.addEventListener('input', performSearch);
});
