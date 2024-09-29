package com.project.demo;


import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
class LivroController {

    @Autowired
    private LivroRepositorio livroRepo;

    public LivroController() {
        // Construtor vazio
    }

    @GetMapping("/api/livros")
    Iterable<Livro> getLivros(@RequestParam Optional<String> genero) {
        return livroRepo.findAll();
    }

    @GetMapping("/api/livros/{id}")
    Optional<Livro> getLivro(@PathVariable long id) {
        return livroRepo.findById(id);
    }

    @PostMapping("/api/livros")
    Livro createLivro(@RequestBody Livro livro) {
        return livroRepo.save(livro);
    }

    @PutMapping("/api/livros/{livroId}")
    Optional<Livro> updateLivro(@RequestBody Livro livroRequest, @PathVariable long livroId) {
        Optional<Livro> opt = livroRepo.findById(livroId);
        if (opt.isPresent()) {
            if (livroRequest.getId() == livroId) {
                livroRepo.save(livroRequest);
                return opt;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Erro ao alterar dados do livro com id " + livroId);
    }

    @DeleteMapping(value = "/api/livros/{id}")
    void deleteLivro(@PathVariable long id) {
        livroRepo.deleteById(id);
    }
}
