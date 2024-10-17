const apiUrl = 'http://localhost:8080';

async function login(correo, contraseña){
    const response = await fetch(`${apiUrl}/logIn`, {
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
        window.location.href = '';
    }

}