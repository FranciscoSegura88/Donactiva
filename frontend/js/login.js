// js/login.js

// Función para manejar el inicio de sesión
function handleLogin() {
    const email = document.getElementById('emailLogin').value;
    const password = document.getElementById('passwordLogin').value;

    const userData = {
        correo: email,
        contraseña: password
    };

    // Realizar la solicitud de inicio de sesión a la API
    fetch('http://localhost:8080/api/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(userData)
    })
    .then(response => {
        if (response.ok) { // Si el código de respuesta es 200-299, asumimos éxito
            return response.text();
        } else {
            return response.text().then(errorData => {
                // Manejar error con detalle proporcionado por el servidor
                throw new Error(errorData.message || 'Error en el registro');
            });
        }
    })
    .then(data => {
        alert('Registro exitoso');
        // Redirigir o realizar otra acción después de registrarse
    })
    .catch(error => {
        console.error('Error al registrarse:', error);
        alert('Hubo un error al intentar registrarse: ' + error.message);
    });
}

// Lógica para detectar la carga del modal y asociar el evento
document.addEventListener("DOMContentLoaded", () => {
    const modalContainer = document.getElementById('modalContainer');

    modalContainer.addEventListener("submit", (event) => {
        const form = event.target;

        if (form.id === "loginForm") {
            event.preventDefault();
            handleLogin(); // Llamar a la función para manejar el login
        }
    });
});
