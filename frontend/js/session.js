document.addEventListener('DOMContentLoaded', () => {
    const token = localStorage.getItem('authToken'); // Obtener el token desde localStorage
    
    if (token) {
        // Si el token existe, actualizamos la sesión.
        updateSession(token);
    } else {
        // Si no hay token, mostramos los botones de login/registro.
        showLoginRegisterButtons();
    }
});

function updateSession(token) {
    try {
        // Decodificamos el token (asumimos que es un JWT)
        const decodedToken = JSON.parse(atob(token.split('.')[1]));
        const username = decodedToken.nombre;  // Extraemos el nombre del usuario desde el token

        // Mostramos el saludo con el nombre del usuario
        const sessionGreeting = document.getElementById('sessionGreeting');
        if (sessionGreeting) {
            sessionGreeting.textContent = `Bienvenido, ${username}`; // Establecer el texto
        } else {
            console.error('El div #sessionGreeting no se encontró en el DOM.');
        }

        // Ocultar los botones de login/register
        document.getElementById('loginBtn').style.display = 'none';
        document.getElementById('registerBtn').style.display = 'none';

        // Mostrar la sección del usuario
        document.getElementById('userSection').classList.remove('hidden'); // Aseguramos que la sección de usuario sea visible

        // Mostrar el menú de usuario (si tienes esta funcionalidad)
        showUserMenu();
    } catch (error) {
        console.error('Error al decodificar el token:', error);
    }
}

function showLoginRegisterButtons() {
    document.getElementById('loginBtn').style.display = 'block';
    document.getElementById('registerBtn').style.display = 'block';
}
