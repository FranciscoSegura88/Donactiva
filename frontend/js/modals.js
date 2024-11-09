document.addEventListener("DOMContentLoaded", () => {
    const loginBtn = document.getElementById('loginBtn');
    const registerBtn = document.getElementById('registerBtn');
    const modalContainer = document.getElementById('modalContainer');

    function openModal(modalType) {
        console.log(`Abriendo el modal de ${modalType}`);  // Verifica si se ejecuta correctamente
        const modalFile = modalType === 'login' ? 'modals/login_modal.html' : 'modals/signup_modal.html';

        fetch(modalFile)
            .then(response => response.text())
            .then(data => {
                // Asegurarse de que el modal se inserte correctamente
                modalContainer.innerHTML = data;

                // Obtener el modal y el fondo
                const modal = document.getElementById(modalType === 'login' ? 'loginModal' : 'signupModal');
                const backdrop = document.createElement('div');
                backdrop.id = 'modalBackdrop';

                // Mostrar el fondo
                document.body.appendChild(backdrop);

                // Mostrar el modal
                modal.style.display = 'flex';  // Hacer visible el modal

                // Botón de cierre para el modal
                const closeModalBtn = modal.querySelector(modalType === 'login' ? '#closeModal' : '#closeSignupModal');
                closeModalBtn.addEventListener('click', () => {
                    closeModal(modal, backdrop);
                });

                // Cerrar el modal al hacer clic fuera de él (en el fondo)
                backdrop.addEventListener('click', (event) => {
                    if (event.target === backdrop) {
                        closeModal(modal, backdrop);
                    }
                });
            })
            .catch(error => console.error('Error al cargar el modal:', error));
    }

    window.closeModal = function(modal, backdrop) {
        modal.style.display = 'none';
        backdrop.remove();
    };

    window.closeModalOnSuccess = function() {
        const modal = modalContainer.querySelector('.modal');
        const backdrop = document.getElementById('modalBackdrop');
        if (modal && backdrop) {
            closeModal(modal, backdrop);
        }
    };

    // Funciones de apertura para los botones de Login y Register
    loginBtn.addEventListener('click', () => {
        console.log('Botón de login presionado');
        openModal('login');
    });

    registerBtn.addEventListener('click', () => {
        console.log('Botón de registro presionado');
        openModal('signup');
    });
});
