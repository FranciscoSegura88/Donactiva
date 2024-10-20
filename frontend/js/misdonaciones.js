document.addEventListener("DOMContentLoaded", function() {
    // Inicializar dropdowns (si es necesario)
    const dropdowns = document.querySelectorAll(".dropdown-btn");
    dropdowns.forEach(btn => {
        btn.addEventListener("click", function() {
            const content = this.nextElementSibling;
            content.style.display = content.style.display === "block" ? "none" : "block";
        });
    });

    // Cargar donaciones
    cargarDonaciones();

    function cargarDonaciones() {
        const token = 'TU_TOKEN_PREFABRICADO'; // Reemplaza con el token real del backend
        
        fetch('http://localhost:8080/api/misDonaciones', {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json'
            }
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Error en la red al obtener las donaciones');
            }
            return response.json();
        })
        .then(data => {
            mostrarDonaciones(data);
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Ocurrió un error al cargar las donaciones');
        });
    }

    function mostrarDonaciones(data) {
        // Elementos donde se mostrarán las donaciones
        const ulPendientes = document.getElementById('donaciones-pendientes');
        const ulCaducadas = document.getElementById('donaciones-caducadas');
        const ulRecaudadas = document.getElementById('donaciones-recaudadas');
        
        let totalPendientes = 0;
        let totalCaducadas = 0;
        let totalRecaudadas = 0;

        // Recorrer las donaciones y asignarlas según su tipo
        data.forEach(donacion => {
            const li = document.createElement('li');
            li.textContent = `${donacion.nombre} - ${donacion.puntos} puntos`;

            switch(donacion.tipo) {
                case 'pendiente':
                    ulPendientes.appendChild(li);
                    totalPendientes += donacion.puntos;
                    break;
                case 'caducada':
                    ulCaducadas.appendChild(li);
                    totalCaducadas += donacion.puntos;
                    break;
                case 'recaudada':
                    ulRecaudadas.appendChild(li);
                    totalRecaudadas += donacion.puntos;
                    break;
            }
        });

        // Mostrar los totales de puntos en cada categoría
        document.getElementById('total-pendientes').textContent = `Total puntos: ${totalPendientes}`;
        document.getElementById('total-caducadas').textContent = `Total puntos: ${totalCaducadas}`;
        document.getElementById('total-recaudadas').textContent = `Total puntos: ${totalRecaudadas}`;
    }

    // --- Funcionalidad del popup ---
    const canjearPuntosBtn = document.getElementById('canjear-puntos'); // Botón para abrir el popup
    const popup = document.getElementById('popup-canjeo'); // Popup del canjeo
    const closeBtn = document.querySelector('.close-btn'); // Botón para cerrar el popup
    const confirmarCanjeoBtn = document.getElementById('confirmar-canjeo'); // Botón de confirmar canjeo
    const formCanjeo = document.getElementById('form-canjeo'); // Formulario de canjeo
    
    // Mostrar el popup cuando se hace clic en "Canjear Puntos"
    canjearPuntosBtn.addEventListener('click', function() {
        popup.style.display = 'block';
    });

    // Cerrar el popup cuando se hace clic en la 'X' (close button)
    closeBtn.addEventListener('click', function() {
        popup.style.display = 'none';
    });

    // Cerrar el popup si se hace clic fuera de él
    window.addEventListener('click', function(event) {
        if (event.target == popup) {
            popup.style.display = 'none';
        }
    });

    // Lógica del botón "Confirmar Canjeo"
    confirmarCanjeoBtn.addEventListener('click', function() {
        const opcionSeleccionada = formCanjeo.querySelector('input[name="opcion-canjeo"]:checked');
        
        if (!opcionSeleccionada) {
            alert('Por favor selecciona una opción antes de confirmar.');
            return;
        }

        const valorOpcion = opcionSeleccionada.value;
        
        // Aquí podrías hacer una llamada al servidor para procesar el canjeo con el valor seleccionado
        alert(`Canjeo confirmado para: ${valorOpcion}`);
        
        // Cerrar el popup después de confirmar
        popup.style.display = 'none';
    });
});
