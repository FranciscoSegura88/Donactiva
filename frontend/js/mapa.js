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
        const center = { lat: 20.66682, lng: -103.39182 };
        const map = new google.maps.Map(document.getElementById("map"), {
            center: center,
            zoom: 12,
        });

        // Llamada al backend para obtener los puntos
    fetch("http://localhost:8080/elegirPunto")
        .then(response => response.json())
        .then(puntos => {
            puntos.forEach(punto => {
                new google.maps.Marker({
                    position: { lat: punto.lat, lng: punto.lng },
                    map: map,
                });
            });
        })
        .catch(error => console.error("Error al cargar los puntos:", error));

        
    }
    
