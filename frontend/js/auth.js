//Envia los datos al backend (o api)

const apiUrl = 'http://localhost:8080'; //Aqui se mandan las solicitudes

async function login(correo, contraseña){ 
    const response = await fetch(`${apiUrl}/api/login`, {
        method: 'GET',
        headers: {
            'Authorization': 'Basic ' + btoa(`${correo}:${contraseña}`),
            'Content-Type': 'application/json'
        }
    });

    if(response.ok){
        const token = await response.text();
        sessionStorage.setItem('token', token);
        //TODO: Cambiar la redireccion a pagina principal
        window.location.hred = '../html/articulos.html';
    }else{
        alert('Credenciales invalidas.')
    }

    function getUserInfoFromToken(){
        const token = sessionStorage.getItem('token');
        if(token){
            const payload = JSON.parse(atob(token.split('.')[1]));

            return payload;
        }

        return null;
    }

    function logout(){
        sessionStorage.removeItem('token');
        //TODO: Agregar redireccion
        window.location.href = '';
    }
}

async function signup(correo, nombre, contraseña) {
    try{

        const data = {
            correo: correo,
            nombre: nombre,
            contraseña: contraseña,
        };

        const response = await fetch(`${apiUrl}/api/signup`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        });

        if(response.ok){
            const result = await response.text();
            alert("Registro exitoso");
        }else{
            alert("Error al registrar el usuario" + response.statusText);
        }
    }catch(error){
        console.error("Error en el registro: ", error);
        alert("Hubo un error en el proceso de registro");
    }
}

async function setArticulos(noPerecederos, higiene, textiles, juguetes) {
    try{
        const data = {
            noPerecederos: noPerecederos,
            higiene: higiene,
            textiles: textiles,
            juguetes: juguetes
        };

        const response = await fetch(`${apiUrl}/api/guardarArticulos`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        });

        if(response.ok){
            const result = await response.text();
            alert("Registro exitoso");
        }else{
            alert("Error al registrar los articulos " + response.statusText);
        }
    }catch(error){
        console.error("Error en el registro de los articulos: ", error);
        alert("Hubo un error en el proceso de registrar los articulos");
    }
}

function updateQuantity(id, increment) {
    const input = document.getElementById(id);
    let value = parseInt(input.value);

    value += increment;
    if (value < 0) {
        value = 0;
    }

    input.value = value;
}