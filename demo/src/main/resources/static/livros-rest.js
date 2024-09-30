async function asyncCriarLivro(dadosLivro, proxsucesso, proxerro) {
    const URL = `/api/livros`;
    const postRequest = {
        method: 'POST',
        body: JSON.stringify(dadosLivro),
        headers: { 'Content-type': 'application/json' }
    };
    fetch(URL, postRequest)
        .then(resposta => { if (!resposta.ok) throw Error(resposta.status); return resposta; })
        .then(resposta => resposta.json())
        .then(jsonResponse => proxsucesso())
        .catch(proxerro);
}

async function asyncLerLivros(proxsucesso, proxerro) {
    const URL = `/api/livros`;
    fetch(URL)
      .then(resposta => { if (!resposta.ok) throw Error(resposta.status); return resposta; }) 
      .then(resposta => resposta.json())
      .then(jsonresponse => proxsucesso(jsonresponse))
      .catch(proxerro);
}

async function asyncLerLivroById(id, proxsucesso, proxerro) {
    const URL = `/api/livros/${id}`;
    fetch(URL)
      .then(resposta => { if (!resposta.ok) throw Error(resposta.status); return resposta; }) 
      .then(resposta => resposta.json())
      .then(jsonresponse => proxsucesso(jsonresponse))
      .catch(proxerro);
}

async function asyncAlterarLivro(dadosLivro, proxsucesso, proxerro) {
    const URL = `/api/livros/${dadosLivro.id}`;
    const putRequest = {
        method: 'PUT',
        body: JSON.stringify(dadosLivro),
        headers: { 'Content-type': 'application/json' }
    };
    fetch(URL, putRequest)
        .then(resposta => { if (!resposta.ok) throw Error(resposta.status); return resposta; })
        .then(resposta => resposta.json())
        .then(jsonResponse => proxsucesso())
        .catch(proxerro);	        	
}

async function asyncApagarLivro(id, proxsucesso, proxerro) {
    const URL = `/api/livros/${id}`;
    const deleteRequest = {
        method: 'DELETE'
    };
    fetch(URL, deleteRequest)
        .then(resposta => { if (!resposta.ok) throw Error(resposta.status); return resposta; })
        .then(resposta => proxsucesso())
        .catch(proxerro);	        		
}
