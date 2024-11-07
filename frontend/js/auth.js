const API_URL = 'http://localhost:8080/api';

async function login(correo, contraseña) {
    try{

        const response = await fetch(`${API_URL}/login`, {
            method: 'POST',
            headers:{
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ correo: correo, contraseña: contraseña }),
        });

        const data = await response.text();

        if(response.ok){
            console.log('Inicio de sesión exitoso');
            
            //Guarda el token
            localStorage.setItem('AuthToken', data.token);
        }else{
            console.error('Error en inicio de sesión');
        }
    }catch(error){
        console.error('Hubo un error al conectarse con el servidor', error);
    }
}

async function register(nombre, correo, contraseña) {
    try{
        const response = await fetch(`{API_URL}/signup`, {
            method: 'POST',
            headers:{
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                nombre: nombre,
                correo: correo,
                contraseña: contraseña
            }),
        });

        const data = await response.text();

        if(response.ok){
            console.log('Registro exitoso', data);
        }else{
            console.error('Error en el registro:', data);
        }
    }catch(error){
        console.error('Hubo un error al conectarse con el servidor', error);
    }
}