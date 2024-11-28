async function asyncCriarFilme(dadosFilme, proxsucesso, proxerro) {
    const token = localStorage.getItem('token');
    if (!token) {
        alert('Token não encontrado. Por favor, faça login novamente.');
        window.location.replace('/html/login.html'); // Redireciona para a página de login
        return;
    }

    const URL = '/api/filmes';
    const postRequest = {
        method: 'POST',
        body: JSON.stringify(dadosFilme),
        headers: { 
            'Content-type': 'application/json',
            'Authorization': `Bearer ${token}` // Incluindo o token no cabeçalho
        }
    };
    
    fetch(URL, postRequest)
        .then(resposta => { if (!resposta.ok) throw Error(resposta.status); return resposta; })
        .then(resposta => resposta.json())
        .then(jsonResponse => proxsucesso())
        .catch(proxerro);
}

async function asyncLerFilmes(proxsucesso, proxerro) {
    const token = localStorage.getItem('token');
    if (!token) {
        alert('Token não encontrado. Por favor, faça login novamente.');
        window.location.replace('/html/login.html'); // Redireciona para a página de login
        return;
    }

    const URL = '/api/filmes';
    fetch(URL, {
        headers: {
            'Authorization': `Bearer ${token}` // Incluindo o token no cabeçalho
        }
    })
        .then(resposta => { if (!resposta.ok) throw Error(resposta.status); return resposta; }) 
        .then(resposta => resposta.json())
        .then(jsonresponse => proxsucesso(jsonresponse))
        .catch(proxerro);
}

async function asyncLerFilmeById(id, proxsucesso, proxerro) {
    const token = localStorage.getItem('token');
    if (!token) {
        alert('Token não encontrado. Por favor, faça login novamente.');
        window.location.replace('/html/login.html'); // Redireciona para a página de login
        return;
    }

    const URL = `/api/filmes/${id}`;
    fetch(URL, {
        headers: {
            'Authorization': `Bearer ${token}` // Incluindo o token no cabeçalho
        }
    })
        .then(resposta => { if (!resposta.ok) throw Error(resposta.status); return resposta; }) 
        .then(resposta => resposta.json())
        .then(jsonresponse => proxsucesso(jsonresponse))
        .catch(proxerro);
}

async function asyncAlterarFilme(dadosFilme, proxsucesso, proxerro) {
    const token = localStorage.getItem('token');
    if (!token) {
        alert('Token não encontrado. Por favor, faça login novamente.');
        window.location.replace('/html/login.html'); // Redireciona para a página de login
        return;
    }

    const URL = `/api/filmes/${dadosFilme.id}`;
    const putRequest = {
        method: 'PUT',
        body: JSON.stringify(dadosFilme),
        headers: { 
            'Content-type': 'application/json',
            'Authorization': `Bearer ${token}` // Incluindo o token no cabeçalho
        }
    };
    
    fetch(URL, putRequest)
        .then(resposta => { if (!resposta.ok) throw Error(resposta.status); return resposta; })
        .then(resposta => resposta.json())
        .then(jsonResponse => proxsucesso())
        .catch(proxerro);	        
}

async function asyncApagarFilme(id, proxsucesso, proxerro) {
    const token = localStorage.getItem('token');
    if (!token) {
        alert('Token não encontrado. Por favor, faça login novamente.');
        window.location.replace('/html/login.html'); // Redireciona para a página de login
        return;
    }

    const URL = `/api/filmes/${id}`;
    const deleteRequest = {
        method: 'DELETE',
        headers: {
            'Authorization': `Bearer ${token}` // Incluindo o token no cabeçalho
        }
    };
    
    fetch(URL, deleteRequest)
        .then(resposta => { if (!resposta.ok) throw Error(resposta.status); return resposta; })
        .then(resposta => proxsucesso())
        .catch(proxerro);	        		
}