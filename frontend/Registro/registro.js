document.addEventListener("DOMContentLoaded", function(){
    const form = document.querySelector('form'); //Selecciona el formulario

    form.addEventListener('submit', function(event){
        event.preventDefault(); //Previene el envio del formulario

        //Captura los valores de los campos
        const email = document.getElementById('email').value;
        const name = document.getElementById('name').value;
        const password = document.getElementById('password').value;
        const confirmPassword = document.getElementById('confirm_password').value;

        //Valida que las contraseñas coincidan
        if(password !== confirmPassword){
            alert("Las constraseñas no coinciden");
            return; //Sale de la funcion si las contraseñas no coinciden
        }

        // Crea el objeto para enviar a la API
        const userData = {
            nombre: name,
            correo: email,
            contraseña: password
        };

        //Realiza la solicitud a la API
        fetch('http://localhost:8080/api/signUp', {
            method: 'POST',
            headers:{
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(userData) //Convierte el objeto a JSON
        })
        .then(response => {
            if(!response.ok){
                throw new Error('Error en la red');
            }
            return response.text();
        })
        .then(data => {
            console.log('Success:', data);
            alert("Cuenta creada exitosamente");
        })
        .catch((error) => {
            console.error('Error:', error); //Maneja errores
            alert("Ocurrio un error al crear la cuenta");
        });
    });
});
