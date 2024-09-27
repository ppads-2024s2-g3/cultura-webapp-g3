async function asyncCriarDesenho(dadosDesenho, proxsucesso, proxerro) {
    const URL = `/api/desenhos`;
    const postRequest = {
        method: 'POST',
        body: JSON.stringify(dadosDesenho),
        headers: { 'Content-type': 'application/json' }
    };
    fetch(URL, postRequest)
        .then(resposta => { 
            if (!resposta.ok) throw Error(resposta.status); 
            return resposta; 
        })
        .then(resposta => resposta.json())
        .then(jsonResponse => proxsucesso())
        .catch(proxerro);
}

async function asyncLerDesenhos(proxsucesso, proxerro) {
    const URL = `/api/desenhos`;
    fetch(URL)
        .then(resposta => { 
            if (!resposta.ok) throw Error(resposta.status); 
            return resposta; 
        })
        .then(resposta => resposta.json())
        .then(jsonresponse => proxsucesso(jsonresponse))
        .catch(proxerro);
}

async function asyncLerDesenhoById(id, proxsucesso, proxerro) {
    const URL = `/api/desenhos/${id}`;
    fetch(URL)
        .then(resposta => { 
            if (!resposta.ok) throw Error(resposta.status); 
            return resposta; 
        })
        .then(resposta => resposta.json())
        .then(jsonresponse => proxsucesso(jsonresponse))
        .catch(proxerro);
}

async function asyncAlterarDesenho(dadosDesenho, proxsucesso, proxerro) {
    const URL = `/api/desenhos/${dadosDesenho.id}`;
    const putRequest = {
        method: 'PUT',
        body: JSON.stringify(dadosDesenho),
        headers: { 'Content-type': 'application/json' }
    };
    fetch(URL, putRequest)
        .then(resposta => { 
            if (!resposta.ok) throw Error(resposta.status); 
            return resposta; 
        })
        .then(resposta => resposta.json())
        .then(jsonResponse => proxsucesso())
        .catch(proxerro);	        	
}

async function asyncApagarDesenho(id, proxsucesso, proxerro) {
    const URL = `/api/desenhos/${id}`;
    const deleteRequest = {
        method: 'DELETE'
    };
    fetch(URL, deleteRequest)
        .then(resposta => { 
            if (!resposta.ok) throw Error(resposta.status); 
            return resposta; 
        })
        .then(resposta => proxsucesso())
        .catch(proxerro);	        		
}
