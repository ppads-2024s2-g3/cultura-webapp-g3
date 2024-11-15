# Testes da API Geeks

## 1. GET /geeks
- **Status:** 200 OK
- **Resposta:** 
[{"id":3,"nome":"Geek c","email":"geekc@exemplo.com"}]

## 2. POST /geeks
- **Status:** 201 Created
- Conteúdo do Post:
{ 
  "nome": "Geek c",
  "email": "geekc@exemplo.com" 
}
- **Resposta:** 
[{"id":3,"nome":"Geek c","email":"geekc@exemplo.com"}]

## 3. DELETE /geeks
- **Status:** 204 No Content
- **Resposta**
[]

