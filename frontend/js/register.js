document.addEventListener("DOMContentLoaded", () => {
    const modalContainer = document.getElementById('modalContainer');
    
    // Verifica que modalContainer exista antes de continuar
    if (modalContainer) {
        modalContainer.addEventListener("submit", (event) => {
            const form = event.target;

            // Asegúrate de que es el formulario correcto
            if (form && form.id === "signupForm") {
                event.preventDefault();

                // Obtener los valores de los campos del formulario
                const name = form.querySelector("input[name='name']").value;
                const email = form.querySelector("input[name='email']").value;
                const password = form.querySelector("input[name='password']").value;
                const confPass = form.querySelector("input[name='confirmPassword']").value;

                // Validar las contraseñas
                if (password !== confPass) {
                    alert("Las contraseñas no coinciden.");
                    return;
                }

                // Aquí puedes agregar la lógica para hacer la petición a la API o cualquier otra acción
                console.log("Registrarse con", name, email, password);

                // Ejemplo de llamada a una función para registrar el usuario
                // register(name, email, password);
            }
        });
    } else {
        console.error("Modal container no encontrado");
    }
});
