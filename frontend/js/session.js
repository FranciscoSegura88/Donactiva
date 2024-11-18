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
        const rol = decodedToken.rol;

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

         // Actualizar el menú desplegable según el rol
        const dropdownMenu = document.getElementById('dropdownMenu');

        if (rol === 'ADMIN') {
            const adminOption = document.createElement('a');
            adminOption.href = "#";
            adminOption.className = "block px-4 py-2 text-gray-700 hover:bg-gray-100";
            adminOption.textContent = "Panel de administración";
            dropdownMenu.insertBefore(adminOption, dropdownMenu.firstChild);
        }

    } catch (error) {
        console.error('Error al decodificar el token:', error);
    }
}

function showLoginRegisterButtons() {
    document.getElementById('loginBtn').style.display = 'block';
    document.getElementById('registerBtn').style.display = 'block';
}

document.getElementById('dropdownBtn').addEventListener('click', () => {
    document.getElementById('dropdownMenu').classList.toggle('hidden');
});

document.getElementById('logoutBtn').addEventListener('click', () => {
    localStorage.removeItem('authToken'); // Eliminar el token
    location.reload(); // Recargar para volver a la vista de login/registro
});
