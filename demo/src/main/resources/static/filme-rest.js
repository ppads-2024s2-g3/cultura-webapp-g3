async function asyncCriarFilme(dadosFilme, proxsucesso, proxerro) {
    const URL = `/api/filmes`;
    const postRequest = {
        method: 'POST',
        body: JSON.stringify(dadosFilme),
        headers: { 'Content-type': 'application/json' }
    };
    fetch(URL, postRequest)
        .then(resposta => { if (!resposta.ok) throw Error(resposta.status); return resposta; })
        .then(resposta => resposta.json())
        .then(jsonResponse => proxsucesso())
        .catch(proxerro);
}

async function asyncLerFilmes(proxsucesso, proxerro) {
    const URL = `/api/filmes`;
    fetch(URL)
        .then(resposta => { if (!resposta.ok) throw Error(resposta.status); return resposta; }) 
        .then(resposta => resposta.json())
        .then(jsonresponse => proxsucesso(jsonresponse))
        .catch(proxerro);
}

async function asyncLerFilmeById(id, proxsucesso, proxerro) {
    const URL = `/api/filmes/${id}`;
    fetch(URL)
        .then(resposta => { if (!resposta.ok) throw Error(resposta.status); return resposta; }) 
        .then(resposta => resposta.json())
        .then(jsonresponse => proxsucesso(jsonresponse))
        .catch(proxerro);
}

async function asyncAlterarFilme(dadosFilme, proxsucesso, proxerro) {
    const URL = `/api/filmes/${dadosFilme.id}`;
    const putRequest = {
        method: 'PUT',
        body: JSON.stringify(dadosFilme),
        headers: { 'Content-type': 'application/json' }
    };
    fetch(URL, putRequest)
        .then(resposta => { if (!resposta.ok) throw Error(resposta.status); return resposta; })
        .then(resposta => resposta.json())
        .then(jsonResponse => proxsucesso())
        .catch(proxerro);	        
}

async function asyncApagarFilme(id, proxsucesso, proxerro) {
    const URL = `/api/filmes/${id}`;
    const deleteRequest = {
        method: 'DELETE'
    };
    fetch(URL, deleteRequest)
        .then(resposta => { if (!resposta.ok) throw Error(resposta.status); return resposta; })
        .then(resposta => proxsucesso())
        .catch(proxerro);	        		
}
