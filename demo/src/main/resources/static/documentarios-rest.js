async function asyncCriarDoc(dadosDocumentario, proxsucesso, proxerro) {
    const URL = `/api/documentarios`;
    const postRequest = {
        method: 'POST',
        body: JSON.stringify(dadosDocumentario),
        headers: { 'Content-type': 'application/json' }
    };
    fetch(URL, postRequest)
        .then(resposta => { if (!resposta.ok) throw Error(resposta.status); return resposta; })
        .then(resposta => resposta.json())
        .then(jsonResponse => proxsucesso())
        .catch(proxerro);
}

async function asyncLerDocs(proxsucesso, proxerro) {
    const URL = `/api/documentarios`;
    fetch(URL)
      .then(resposta => { if (!resposta.ok) throw Error(resposta.status); return resposta; }) 
      .then(resposta => resposta.json())
      .then(jsonresponse => proxsucesso(jsonresponse))
      .catch(proxerro);
}

async function asyncLerDocById(id, proxsucesso, proxerro) {
    const URL = `/api/documentarios/${id}`;
    fetch(URL)
      .then(resposta => { if (!resposta.ok) throw Error(resposta.status); return resposta; }) 
      .then(resposta => resposta.json())
      .then(jsonresponse => proxsucesso(jsonresponse))
      .catch(proxerro);
}

async function asyncAlterarDoc(dadosDocumentario, proxsucesso, proxerro) {
    const URL = `/api/documentarios/${dadosDocumentario.id}`;
    const putRequest = {
        method: 'PUT',
        body: JSON.stringify(dadosDocumentario),
        headers: { 'Content-type': 'application/json' }
    };
    fetch(URL, putRequest)
        .then(resposta => { if (!resposta.ok) throw Error(resposta.status); return resposta; })
        .then(resposta => resposta.json())
        .then(jsonResponse => proxsucesso())
        .catch(proxerro);
}

async function asyncApagarDoc(id, proxsucesso, proxerro) {
    const URL = `/api/documentarios/${id}`;
    const deleteRequest = {
        method: 'DELETE'
    };
    fetch(URL, deleteRequest)
        .then(resposta => { if (!resposta.ok) throw Error(resposta.status); return resposta; })
        .then(resposta => proxsucesso())
        .catch(proxerro);
}
