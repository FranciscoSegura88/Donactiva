// articulos.js

    const showMapModalBtn = document.getElementById("elegirUbicacion");
    console.log("Botón 'Elegir ubicación' detectado"); // Verificación
    if (showMapModalBtn) {
        console.log("Botón 'Elegir ubicación' detectado"); // Verificación
        showMapModalBtn.addEventListener("click", async function (e) {
            e.preventDefault();

            // Cargar el contenido del modal desde mapa_modal.html
            const response = await fetch("mapa_modal.html");
            const modalHtml = await response.text();
            document.getElementById("modalContainer").innerHTML = modalHtml;

            // Mostrar el modal y el overlay
            const modal = document.getElementById("modal");
            const overlay = document.getElementById("overlay");

            if (modal && overlay) {
                modal.style.display = "block";
                overlay.style.display = "block";

                // Configurar cierre al hacer clic en el overlay
                overlay.addEventListener("click", () => {
                    modal.style.display = "none";
                    overlay.style.display = "none";
                });
            } else {
                console.error("No se encontraron elementos modal u overlay en mapa_modal.html");
            }
        });
    } else {
        console.error("Botón 'Elegir ubicación' no encontrado en articulos.html");
    }

