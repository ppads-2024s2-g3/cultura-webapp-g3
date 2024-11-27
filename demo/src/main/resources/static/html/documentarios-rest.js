async function asyncCriarDoc(dadosDocumentario, proxsucesso, proxerro) {
    const token = localStorage.getItem('token');
    if (!token) {
        alert('Token não encontrado. Por favor, faça login novamente.');
        window.location.replace('/html/login.html'); // Redireciona para a página de login
        return;
    }

    const URL = `/api/documentarios`;
    const postRequest = {
        method: 'POST',
        body: JSON.stringify(dadosDocumentario),
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

async function asyncLerDocs(proxsucesso, proxerro) {
    const token = localStorage.getItem('token');
    if (!token) {
        alert('Token não encontrado. Por favor, faça login novamente.');
        window.location.replace('/html/login.html'); // Redireciona para a página de login
        return;
    }

    const URL = `/api/documentarios`;
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

async function asyncLerDocById(id, proxsucesso, proxerro) {
    const token = localStorage.getItem('token');
    if (!token) {
        alert('Token não encontrado. Por favor, faça login novamente.');
        window.location.replace('/html/login.html'); // Redireciona para a página de login
        return;
    }

    const URL = `/api/documentarios/${id}`;
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

async function asyncAlterarDoc(dadosDocumentario, proxsucesso, proxerro) {
    const token = localStorage.getItem('token');
    if (!token) {
        alert('Token não encontrado. Por favor, faça login novamente.');
        window.location.replace('/html/login.html'); // Redireciona para a página de login
        return;
    }

    const URL = `/api/documentarios/${dadosDocumentario.id}`;
    const putRequest = {
        method: 'PUT',
        body: JSON.stringify(dadosDocumentario),
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

async function asyncApagarDoc(id, proxsucesso, proxerro) {
    const token = localStorage.getItem('token');
    if (!token) {
        alert('Token não encontrado. Por favor, faça login novamente.');
        window.location.replace('/html/login.html'); // Redireciona para a página de login
        return;
    }

    const URL = `/api/documentarios/${id}`;
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