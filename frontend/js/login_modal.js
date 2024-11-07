// modal_login.js
function handleLogin() {
    const loginForm = document.getElementById('loginForm');

    if (!loginForm) return; // Asegura que el formulario existe antes de proceder

    loginForm.addEventListener('submit', function(event) {
        event.preventDefault();  // Evita el comportamiento por defecto del formulario
        const email = document.getElementById('emailLogin').value;
        const password = document.getElementById('passwordLogin').value;

        const data = {
            correo: email,
            contraseña: password
        };

        const response = fetch('http://localhost:8080/api/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data),
        })
        .then(response => response.json())
        .then(data => {
            if (data.ok) {
                localStorage.setItem('authToken', data.token);
                alert('Inicio de sesión exitoso');
                const modal = document.getElementById('modal');
                if (modal) {
                    modal.classList.add('hidden');
                    document.body.classList.remove('modal-open');
                }
            } else {
                alert('Error al iniciar sesión: ' + data.message);
            }
        })
        .catch(error => {
            console.error('Error al iniciar sesión:', error);
            alert('Hubo un problema al iniciar sesión. Por favor intente de nuevo.');
        });
    });
}
