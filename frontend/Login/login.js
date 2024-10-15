document.addEventListener("DOMContentLoaded", function(){
    const form = document.querySelector('form');
    
    form.addEventListener('submit', function(event){
        event.preventDefault();

        const email = encodeURIComponent(document.getElementById('email').value);
        const password = encodeURIComponent(document.getElementById('password').value);

        fetch(`http://localhost:8080/api/logIn?correo=${email}&contraseña=${password}`, {
            method: 'GET',
        })
        .then(response => {
            if(!response.ok){
                throw new Error('Error en la red');
            }
            return response.text();
        })
        .then(data => {
            console.log('Success:', data);
            alert(data);
            form.reset();
        })
        .catch((error) => {
            console.error('Error:', error);
            alert("Ocurrio un error al iniciar sesión");
        });
    });
});