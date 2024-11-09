// js/register.js

// Función para manejar el registro de usuario
function handleRegister() {
    const nombre = document.getElementById('name').value;
    const correo = document.getElementById('email').value;
    const contraseña = document.getElementById('password').value;
    const confirmContra = document.getElementById('confirmPassword').value;

    if (contraseña !== confirmContra) {
        alert("Las contraseñas no coinciden.");
        return;
    }

    const userData = {
        nombre: nombre,
        correo: correo,
        contraseña: contraseña
    };

    // Realizar la solicitud de registro a la API
    const response = fetch('http://localhost:8080/api/signup', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(userData)
    })
    .then(response => {
        if (response.ok) { // Si el código de respuesta es 200-299, asumimos éxito
            window.closeModalOnSuccess(); // Cerrar el modal si el login es exitoso
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

// Lógica para detectar el envío del formulario de registro en el modal
document.addEventListener("DOMContentLoaded", () => {
    const modalContainer = document.getElementById('modalContainer');

    if (modalContainer) {
        modalContainer.addEventListener("submit", (event) => {
            const form = event.target;

            if (form && form.id === "signupForm") {
                event.preventDefault();
                handleRegister(); // Llamar a la función para manejar el registro
            }
        });
    } else {
        console.error("Modal container no encontrado");
    }
});
