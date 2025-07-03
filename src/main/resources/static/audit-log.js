document.addEventListener('DOMContentLoaded', () => {
    // Charger les logs d'audit depuis l'API
    const auditLogTableBody = document.querySelector('#audit-log-table tbody');
    fetch('/api/audit-logs')
        .then(response => response.json())
        .then(auditLogs => {
            auditLogs.forEach(log => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${log.utilisateur}</td>
                    <td>${log.action}</td>
                    <td>${log.date}</td>
                    <td>${log.details}</td>
                `;
                auditLogTableBody.appendChild(row);
            });
        });

    // Charger l'historique des actions depuis l'API
    const actionHistoryTableBody = document.querySelector('#action-history-table tbody');
    fetch('/api/action-history')
        .then(response => response.json())
        .then(actionHistory => {
            actionHistory.forEach(action => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${action.utilisateur}</td>
                    <td>${action.typeAction}</td>
                    <td>${action.date}</td>
                    <td>${action.statut}</td>
                `;
                actionHistoryTableBody.appendChild(row);
            });
        });
});
