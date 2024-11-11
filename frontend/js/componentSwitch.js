// Función para cargar el componente desde un archivo HTML
function loadComponent(componentName) {
    const rightside = document.getElementById('rightside');  // Contenedor donde se cargan los componentes

    fetch(`modals/${componentName}.html`)  // Cargar el componente desde la carpeta 'components'
        .then(response => response.text())
        .then(data => {
            rightside.innerHTML = data;  // Insertar el contenido del componente cargado

            // Si el componente cargado es 'tutorial', agregar el evento para el botón "Siguiente"
            if (componentName === "tutorial") {
                const nextButton = document.querySelector(".btn-siguiente");  // Obtener el botón "Siguiente"
                nextButton.addEventListener("click", () => {
                    loadComponent("articulos");  // Cargar el componente 'articulos'
                });
            }

            // Si el componente cargado es 'articulos', agregar el evento para el botón "Regresar"
            if (componentName === "articulos") {
                const backButton = document.querySelector(".btn-regresar");  // Obtener el botón "Regresar"
                backButton.addEventListener("click", () => {
                    loadComponent("tutorial");  // Cargar el componente 'tutorial'
                });
            }
        })
        .catch(error => {
            console.error("Error al cargar el componente:", error);
        });
}

// Llamar a la función para mostrar el tutorial al cargar la página
document.addEventListener("DOMContentLoaded", () => {
    loadComponent("tutorial");  // Mostrar el tutorial por defecto
});

// Función para actualizar cantidad
function updateQuantity(id, change) {
    const input = document.getElementById(id);
    let currentValue = parseInt(input.value) || 0;
    currentValue += change;
    input.value = Math.max(0, currentValue); // Evita valores negativos
}

// Función para asignar eventos a los botones al cargar el componente
function assignQuantityButtons() {
    document.querySelectorAll('.btn-decrement, .btn-increment').forEach(button => {
        button.addEventListener('click', function () {
            const targetId = this.getAttribute('data-target');
            const change = parseInt(this.getAttribute('data-change'));
            updateQuantity(targetId, change);
        });
    });
}

// Llama a assignQuantityButtons cuando se cargue el componente
document.addEventListener('DOMContentLoaded', () => {
    assignQuantityButtons();
});
