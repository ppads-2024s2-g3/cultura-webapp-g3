# Testes da API Avaliacao

## 1. GET /avaliacoes
- **Status:** 200 OK
- **Resposta:** 
[{"id":3,"nome":"Geek c","email":"geekc@exemplo.com"}]

## 2. POST /avaliacoes
- **Status:** 201 Created
- Conteúdo do Post:
{ 
  "nome": "Geek c",
  "email": "geekc@exemplo.com" 
}
- **Resposta:** 
[{"id":3,"nome":"Geek c","email":"geekc@exemplo.com"}]

## 3. DELETE /avaliacoes
- **Status:** 204 No Content
- **Resposta**
[]

