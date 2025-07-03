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
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('Échec de la connexion');
            }
        })
        .then(() => {
            alert('Connexion réussie !');
            window.location.href = '/dashboard';
        })
        .catch(error => {
            alert(error.message);
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

        fetch('/api/users', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(registerData),
        })
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('Échec de l\'inscription');
            }
        })
        .then(() => {
            alert('Inscription réussie !');
            window.location.href = '/login';
        })
        .catch(error => {
            alert(error.message);
        });
    });
});
