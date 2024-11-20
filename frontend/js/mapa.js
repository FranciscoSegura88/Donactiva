    fetch("http://localhost:8080/api/google-maps-api-key")
        .then(response => response.text()) // Obtener la clave como texto
        .then(apiKey => {
            const script = document.createElement("script");
            script.src = `https://maps.googleapis.com/maps/api/js?key=${apiKey}&callback=initMap`;
            script.async = true;
            document.head.appendChild(script);
        })
        .catch(error => {
            console.error("Error al obtener la clave de la API:", error);
        });

    // API Key de Google Maps cargada aquí
    function initMap() {
        // Configuración del mapa, centrado en coordenadas de ejemplo
        const center = { lat: 20.62, lng: -103.35 };
        const map = new google.maps.Map(document.getElementById("map"), {
            center: center,
            zoom: 11.5,
        });

        // Llamada al backend para obtener los puntos
    fetch("http://localhost:8080/api/obtenerLocalizaciones")
        .then(response => response.json())
        .then(puntos => {
            puntos.forEach(punto => {
                const marcador = new google.maps.Marker({
                    position: { lat: punto.longitud, lng: punto.latitud },
                    map: map,
                    title: punto.nombre,
                    id: punto.idLocalizacion,
                });

                marcador.addListener("click", () => {
                    alert(`Seleccionaste: ${punto.nombre} , ${punto.idLocalizacion}`);

                    const ubicacion = {
                        id: punto.idLocalizacion, 
                    };
                    
                    localStorage.setItem("ubicacion", JSON.stringify(ubicacion));
                });

            });
        })
        .catch(error => console.error("Error al cargar los puntos:", error));
    }
    
    function cancelarOperacion(){
        localStorage.removeItem("articulos");
        localStorage.removeItem("ubicacion");
        alert("Operacion cancelada.");
    }

    async function confirmarOperacion() {
        const token = localStorage.getItem("authToken");
    
        let id_usuario;

        try {

        } catch (error) {
            console.error("Error al decodificar el token:", error);
            alert("El token de autenticación es inválido. Por favor, inicia sesión nuevamente.");
            return;
        }
    
        const articulos = localStorage.getItem("articulos");
        const ubicacion = localStorage.getItem("ubicacion");
    
        // Validar que existan artículos y una ubicación seleccionada
        if (!articulos || articulos.length === 0) {
            alert("Por favor, selecciona al menos un artículo antes de confirmar.");
            return;
        }
    
        if (!ubicacion) {
            alert("Por favor, selecciona una ubicación antes de confirmar.");
            return;
        }
    
        try {
            // Guardar los artículos
            const responseArticulos = await fetch("http://localhost:8080/api/guardarArticulos", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    Authorization: `Bearer ${token}`, // Token para autenticación
                },
                body: articulos,
            });
    
            if (!responseArticulos.ok) {
                const errorData = await responseArticulos.json();
                console.error("Error al guardar los artículos:", errorData);
                alert("Hubo un error al guardar los artículos. Intenta nuevamente.");
                return;
            }
    
            // Obtener la respuesta del backend y extraer el ID del artículo
            const articuloGuardado = await responseArticulos.json();
            const idArticulo = JSON.stringify(articuloGuardado.idArticulo);
            console.log("Artículo guardado con ID:", idArticulo);
    
            // Guardar la donación
            const donacionData = {
                idUsuario: id_usuario,
                idArticulo: idArticulo,
                ubicacion: ubicacion.nombre,
            };

            const responseDonacion = await fetch("http://localhost:8080/api/confirmarDonacion", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    Authorization: `Bearer ${token}`,
                },
                body: donacionData,
            });
    
            if (!responseDonacion.ok) {
                const errorData = await responseDonacion.json();
                console.error("Error al guardar la donación:", errorData);
                alert("Hubo un error al guardar la donación. Intenta nuevamente.");
                return;
            }
    
            const donacionGuardada = await responseDonacion.json();
            console.log("Donación guardada con éxito:", donacionGuardada);
    
            alert("Gracias por tu donación. Ha sido registrada correctamente.");
    
            // Limpiar el localStorage
            localStorage.removeItem("articulos");
            localStorage.removeItem("ubicacion");
            localStorage.removeItem("idArticuloGuardado");
        } catch (error) {
            console.error("Error en la operación:", error);
            alert("Hubo un error en la operación. Por favor, intenta nuevamente.");
        }
    }
    

    document.addEventListener("DOMContentLoaded", () => {
        const elegirUbicacionButton = document.getElementById("closeModal");
        const confirmarButton = document.getElementById("confirmArti");
        elegirUbicacionButton.addEventListener("click", cancelarOperacion);
        confirmarButton.addEventListener("click", confirmarOperacion);
    });