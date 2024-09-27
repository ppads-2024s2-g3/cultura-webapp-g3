async function asyncLerLivros(sucesso, falha) {
    try {
        const resposta = await fetch('/livros');
        if (!resposta.ok) {
            throw new Error(resposta.status);
        }
        const livros = await resposta.json();
        sucesso(livros);
    } catch (erro) {
        falha(erro);
    }
}

async function asyncLerLivroById(id, sucesso, falha) {
    try {
        const resposta = await fetch(`/livros/${id}`);
        if (!resposta.ok) {
            throw new Error(resposta.status);
        }
        const livro = await resposta.json();
        sucesso(livro);
    } catch (erro) {
        falha(erro);
    }
}

async function asyncCriarLivro(livro, sucesso, falha) {
    try {
        const resposta = await fetch('/livros', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(livro)
        });
        if (!resposta.ok) {
            throw new Error(resposta.status);
        }
        sucesso();
    } catch (erro) {
        falha(erro);
    }
}

async function asyncAlterarLivro(livro, sucesso, falha) {
    try {
        const resposta = await fetch(`/livros/${livro.id}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(livro)
        });
        if (!resposta.ok) {
            throw new Error(resposta.status);
        }
        sucesso();
    } catch (erro) {
        falha(erro);
    }
}

async function asyncApagarLivro(id, sucesso, falha) {
    try {
        const resposta = await fetch(`/livros/${id}`, { method: 'DELETE' });
        if (!resposta.ok) {
            throw new Error(resposta.status);
        }
        sucesso();
    } catch (erro) {
        falha(erro);
    }
}
