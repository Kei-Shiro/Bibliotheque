document.addEventListener('DOMContentLoaded', () => {
    // Gestion du formulaire de connexion
    const loginForm = document.querySelector('#login-form');
    loginForm.addEventListener('submit', (event) => {
        event.preventDefault();

        const loginData = {
            username: document.querySelector('#login-username').value,
            password: document.querySelector('#login-password').value,
        };

        fetch('/api/auth/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(loginData),
        })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                alert('Connexion réussie !');
                window.location.href = '/dashboard';
            } else {
                alert('Échec de la connexion : ' + data.message);
            }
        });
    });

    // Gestion du formulaire d'inscription
    const registerForm = document.querySelector('#register-form');
    registerForm.addEventListener('submit', (event) => {
        event.preventDefault();

        const registerData = {
            username: document.querySelector('#register-username').value,
            password: document.querySelector('#register-password').value,
            email: document.querySelector('#register-email').value,
        };

        fetch('/api/auth/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(registerData),
        })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                alert('Inscription réussie !');
                window.location.href = '/login';
            } else {
                alert('Échec de l_inscription : ' + data.message);
            }
        });
    });
});
