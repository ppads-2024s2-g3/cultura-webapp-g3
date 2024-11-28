async function asyncCriarSerie(dadosSerie, proxsucesso, proxerro) {
    const token = localStorage.getItem('token');
    if (!token) {
        alert('Token não encontrado. Por favor, faça login novamente.');
        window.location.replace('/html/login.html'); // Redireciona para a página de login
        return;
    }

    const URL = `/api/series`;
    const postRequest = {
        method: 'POST',
        body: JSON.stringify(dadosSerie),
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

async function carregarSeries() {
    const token = localStorage.getItem('token');
    if (!token) {
        alert('Token não encontrado. Por favor, faça login novamente.');
        window.location.replace('/html/login.html'); // Redireciona para a página de login
        return;
    }

    asyncLerSerieById(
        series => {
            console.log("Séries carregadas:", series);
            series.forEach(serie => {
                console.log(`ID: ${serie.id}, Título: ${serie.titulo}`);
                // Renderizar série na interface
            });
        },
        erro => console.error("Erro ao carregar séries:", erro)
    );
}

async function asyncLerSeriesById(proxsucesso, proxerro) {
    const token = localStorage.getItem('token');
    if (!token) {
        alert('Token não encontrado. Por favor, faça login novamente.');
        window.location.replace('/html/login.html'); // Redireciona para a página de login
        return;
    }

    const URL = `/api/series`; // Endpoint para obter todas as séries
    fetch(URL, {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${token}` // Incluindo o token no cabeçalho
        }
    })
        .then(resposta => {
            if (!resposta.ok) throw Error(resposta.status);
            return resposta.json();
        })
        .then(jsonResponse => proxsucesso(jsonResponse))
        .catch(proxerro);
}

async function asyncAlterarSerie(dadosSerie, proxsucesso, proxerro) {
    const token = localStorage.getItem('token');
    if (!token) {
        alert('Token não encontrado. Por favor, faça login novamente.');
        window.location.replace('/html/login.html'); // Redireciona para a página de login
        return;
    }

    const URL = `/api/series/${dadosSerie.id}`;
    const putRequest = {
        method: 'PUT',
        body: JSON.stringify(dadosSerie),
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

async function asyncApagarSerie(id, proxsucesso, proxerro) {
    if (!id || id <= 0) {
        console.error(`ID inválido: ${id}. Não é possível excluir.`);
        proxerro(new Error(`ID inválido: ${id}`));
        return;
    }

    const token = localStorage.getItem('token');
    if (!token) {
        alert('Token não encontrado. Por favor, faça login novamente.');
        window.location.replace('/html/login.html'); // Redireciona para a página de login
        return;
    }

    const URL = `/api/series/${id}`;
    const deleteRequest = {
        method: 'DELETE',
        headers: { 
            'Authorization': `Bearer ${token}` // Incluindo o token no cabeçalho
        }
    };

    fetch(URL, deleteRequest)
        .then(resposta => {
            if (!resposta.ok) throw Error(`Erro ao excluir série com ID ${id}: ${resposta.status}`);
            console.log(`Série com ID ${id} excluída com sucesso!`);
            return resposta;
        })
        .then(() => proxsucesso())
        .catch(erro => {
            console.error(`Erro na exclusão: ${erro.message}`);
            proxerro(erro);
        });
}
