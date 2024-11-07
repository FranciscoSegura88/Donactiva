// js/login.js
document.addEventListener("DOMContentLoaded", () => {
    const modalContainer = document.getElementById('modalContainer');

    // Detecta cuando se carga el formulario de login dentro del modal
    modalContainer.addEventListener("submit", (event) => {
        const form = event.target;

        if (form.id === "loginForm") {  // Asegúrate de que es el formulario correcto
            event.preventDefault();
            
            const email = form.querySelector("input[name='email']").value;
            const password = form.querySelector("input[name='password']").value;

            // Aquí puedes llamar a tu función de autenticación con email y password
            console.log("Iniciar sesión con", email, password);
        }
    });
});
