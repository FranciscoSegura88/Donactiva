    fetch("http://localhost:8080/google-maps-api-key")
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
    fetch("http://localhost:8080/elegirPunto")
        .then(response => response.json())
        .then(puntos => {
            puntos.forEach(punto => {
                const marcador = new google.maps.Marker({
                    position: { lat: punto.lat, lng: punto.lng },
                    map: map,
                    title: punto.nombre,
                });

                marcador.addListener("click", () => {
                    alert(`Seleccionaste: ${punto.nombre}`);

                    const ubicacion = {
                        nombre: punto.nombre, 
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

    function confirmarOperacion(){

        const ubicacion = localStorage.getItem("ubicacion");

        if(!ubicacion){
            alert("Por favor, selecciona una ubicacion antes de confirmar.");
        } else {

            //TODO: Guardar los articulos en la base de datos


            /*TODO: Una vez guardada obtener la id de los articulos para mandarlo a la donacion,
            donacion(id_usuario, id_ubicacion, )  tomada desde el token, al igual que la id de localizacion
            */

            alert("Gracias por su donacion");
        }
    }

    document.addEventListener("DOMContentLoaded", () => {
        const elegirUbicacionButton = document.getElementById("closeModal");
        const confirmarButton = document.getElementById("confirmArti");
        elegirUbicacionButton.addEventListener("click", cancelarOperacion);
        confirmarButton.addEventListener("click", confirmarOperacion);
    });