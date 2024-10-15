document.addEventListener("DOMContentLoaded", function() {
    const form = document.querySelector('form');

    form.addEventListener('submit', function(event) {
        event.preventDefault();

        // Obtener los valores introducidos por el usuario
        const noPerecederos = parseInt(document.getElementById('no-perecederos').value) || 0;
        const textiles = parseInt(document.getElementById('textiles').value) || 0;
        const limpieza = parseInt(document.getElementById('limpieza').value) || 0;
        const higiene = parseInt(document.getElementById('higiene').value) || 0;

        // Crear el cuerpo del JSON con la información
        const donationData = {
            noPerecederos: noPerecederos,
            textiles: textiles,
            limpieza: limpieza,
            higiene: higiene
        };

        // Enviar los datos al servidor mediante una solicitud POST
        fetch('http://localhost:8080/api/donar', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(donationData)
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Error en la red');
            }
            return response.text();
        })
        .then(data => {
            console.log('Success:', data);
            alert('Donación enviada con éxito');
            form.reset(); // Limpiar el formulario después de enviar
        })
        .catch((error) => {
            console.error('Error:', error);
            alert("Ocurrió un error al enviar la donación");
        });
    });
});
