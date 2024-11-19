function openModal() {
    document.getElementById("modalOverlay").style.display = "flex";
}

// Función para cerrar el modal
function closeModal() {
    document.getElementById("modalOverlay").style.display = "none";
}

// Función para cargar el componente desde un archivo HTML
function loadComponent(componentName) {
    const rightside = document.getElementById('rightside');  // Contenedor donde se cargan los componentes

    fetch(`modals/${componentName}.html`)  // Cargar el componente desde la carpeta 'components'
        .then(response => response.text())
        .then(data => {
            rightside.innerHTML = data;

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

            // Si el componente cargado es 'articulos', agregar el evento para el botón "Elegir Ubicación"
            if (componentName === "articulos") {
                const elegirUbicacionButton = document.getElementById("elegirUbicacion");  // Botón en el componente 'articulos'
                
                if (elegirUbicacionButton) {
                    elegirUbicacionButton.addEventListener("click", () => {
                        // Mostrar el modal superpuesto desde el index
                        const modalOverlay = document.getElementById("modalOverlay");
                        modalOverlay.style.display = "flex";  // Asegura que sea 'flex' para centrado con CSS
                    });
                }
            }
            
            // Agregar lógica para cerrar el modal desde el botón dentro del modal
            const closeModalButton = document.getElementById("closeModal");
            if (closeModalButton) {
                closeModalButton.addEventListener("click", () => {
                    const modalOverlay = document.getElementById("modalOverlay");
                    modalOverlay.style.display = "none";  // Oculta el modal
                });
            }

        })
        .catch(error => {
            console.error("Error al cargar el componente:", error);
        });
}

// Cerrar modal
function closeModal() {
    const modalOverlay = document.getElementById('modalOverlay');
    modalOverlay.style.display = 'none'; // Ocultar el modal
    modalOverlay.innerHTML = ''; // Limpiar el contenido del modal
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

// Función para guardar las cantidades
function setItems() {
    const items = {
        noPerecederos: parseInt(document.getElementById("no-perecederos").value, 10),
        higiene: parseInt(document.getElementById("higiene").value, 10),
        textiles: parseInt(document.getElementById("textiles").value, 10),
        juguetes: parseInt(document.getElementById("juguetes").value, 10),
    };

    // Calcular el total de artículos seleccionados
    const totalItems = Object.values(items).reduce((sum, val) => sum + val, 0);

    // Validar si se han seleccionado artículos
    if (totalItems === 0) {
        alert("No has ingresado ningún artículo.");
    } else {
        // Guardar los datos en localStorage
        localStorage.setItem("articulos", JSON.stringify(items));
    }
}

// Asignar evento al botón "Elegir ubicación"
document.addEventListener("DOMContentLoaded", () => {
    const elegirUbicacionButton = document.getElementById("elegirUbicacion");
    elegirUbicacionButton.addEventListener("click", setItems); 
});