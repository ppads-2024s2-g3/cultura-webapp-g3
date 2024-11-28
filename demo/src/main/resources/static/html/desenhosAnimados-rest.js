async function asyncCriarDesenho(dadosDesenho, proxsucesso, proxerro) {
    const token = localStorage.getItem('token');
    if (!token) {
        alert('Token não encontrado. Por favor, faça login novamente.');
        window.location.replace('/html/login.html'); // Redireciona para a página de login
        return;
    }

    const URL = "/api/desenhos";
    const postRequest = {
        method: 'POST',
        body: JSON.stringify(dadosDesenho), // Confirma o envio do JSON
        headers: { 
            'Content-type': 'application/json',
            'Authorization': `Bearer ${token}` // Adiciona o token no cabeçalho
        }
    };
    
    console.log("Payload enviado:", postRequest.body); // Verifica o que está sendo enviado

    try {
        const resposta = await fetch(URL, postRequest);
        if (!resposta.ok) throw new Error(resposta.status);
        const jsonResponse = await resposta.json();
        proxsucesso(jsonResponse);
    } catch (erro) {
        proxerro(erro);
    }
}

async function asyncLerDesenhos(proxsucesso, proxerro) {
    const token = localStorage.getItem('token');
    if (!token) {
        alert('Token não encontrado. Por favor, faça login novamente.');
        window.location.replace('/html/login.html'); // Redireciona para a página de login
        return;
    }

    const URL = "/api/desenhos";
    
    try {
        const resposta = await fetch(URL, {
            headers: {
                'Authorization': `Bearer ${token}` // Incluindo o token no cabeçalho
            }
        });
        if (!resposta.ok) throw new Error(resposta.status);
        const jsonResponse = await resposta.json();
        proxsucesso(jsonResponse);
    } catch (erro) {
        proxerro(erro);
    }
}

async function asyncLerDesenhoById(id, proxsucesso, proxerro) {
    const token = localStorage.getItem('token');
    if (!token) {
        alert('Token não encontrado. Por favor, faça login novamente.');
        window.location.replace('/html/login.html'); // Redireciona para a página de login
        return;
    }

    const URL = `/api/desenhos/${id}`;
    
    try {
        const resposta = await fetch(URL, {
            headers: {
                'Authorization': `Bearer ${token}` // Incluindo o token no cabeçalho
            }
        });
        if (!resposta.ok) throw new Error(resposta.status);
        const jsonResponse = await resposta.json();
        proxsucesso(jsonResponse);
    } catch (erro) {
        proxerro(erro);
    }
}

async function asyncAlterarDesenho(dadosDesenho, proxsucesso, proxerro) {
    const token = localStorage.getItem('token');
    if (!token) {
        alert('Token não encontrado. Por favor, faça login novamente.');
        window.location.replace('/html/login.html'); // Redireciona para a página de login
        return;
    }

    const URL = `/api/desenhos/${dadosDesenho.id}`;
    const putRequest = {
        method: 'PUT',
        body: JSON.stringify(dadosDesenho),
        headers: { 
            'Content-type': 'application/json',
            'Authorization': `Bearer ${token}` // Adiciona o token no cabeçalho
        }
    };
    
    try {
        const resposta = await fetch(URL, putRequest);
        if (!resposta.ok) throw new Error(resposta.status);
        const jsonResponse = await resposta.json();
        proxsucesso(jsonResponse);
    } catch (erro) {
        proxerro(erro);
    }
}

async function asyncApagarDesenho(id, proxsucesso, proxerro) {
    const token = localStorage.getItem('token');
    if (!token) {
        alert('Token não encontrado. Por favor, faça login novamente.');
        window.location.replace('/html/login.html'); // Redireciona para a página de login
        return;
    }

    const URL = `/api/desenhos/${id}`;
    const deleteRequest = {
        method: 'DELETE',
        headers: {
            'Authorization': `Bearer ${token}` // Incluindo o token no cabeçalho
        }
    };
    
    try {
        const resposta = await fetch(URL, deleteRequest);
        if (!resposta.ok) throw new Error(resposta.status);
        proxsucesso();
    } catch (erro) {
        proxerro(erro);
    }
}
