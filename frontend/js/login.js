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

            window.closeModalOnSuccess(); // Cerrar el modal si el login es exitoso

            const modal = document.getElementById('loginModal');
            const backdrop = document.getElementById('modalBackdrop');
            closeModal(modal, backdrop);

            return response.text(); // Retorna el token en texto
        } else {
            return response.text().then(errorData => {
                // Manejar error con detalle proporcionado por el servidor
                throw new Error(errorData.message || 'Error al iniciar sesión');
            });
        }
    })
    .then(token => {
        // Almacenar el token en localStorage
        localStorage.setItem('token', token);
        
        // Decodificar el token para obtener el nombre del usuario (suponiendo que sea un JWT)
        const decodedToken = JSON.parse(atob(token.split('.')[1])); // Decodificamos el payload del token
        const username = decodedToken.nombre; // Aquí extraemos el nombre

        // Mostrar el saludo y ocultar los botones de login y registro
        document.getElementById('authSection').classList.add('hidden'); // Ocultar login/register
        document.getElementById('userSection').classList.remove('hidden'); // Mostrar saludo

        // Mostrar el saludo con el nombre del usuario
        document.getElementById('greeting').textContent = `Bienvenido, ${username}`;
        
        // Redirigir o realizar otra acción después de iniciar sesión, por ejemplo:
        // window.location.href = "/dashboard"; // Ejemplo de redirección
    })
    .catch(error => {
        console.error('Error al iniciar sesión:', error);
        alert('Hubo un error al intentar iniciar sesión: ' + error.message);
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
