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
class FilmeController {

    @Autowired
    private FilmeRepositorio filmeRepo;

    public FilmeController() {
        // Construtor vazio
    }

    @GetMapping("/api/filmes")
    Iterable<Filme> getFilmes(@RequestParam Optional<String> genero) {
        return filmeRepo.findAll();
    }

    @GetMapping("/api/filmes/{id}")
    Optional<Filme> getFilme(@PathVariable long id) {
        return filmeRepo.findById(id);
    }

    @PostMapping("/api/filmes")
    Filme createFilme(@RequestBody Filme filme) {
        return filmeRepo.save(filme);
    }

    @PutMapping("/api/filmes/{filmeId}")
    Optional<Filme> updateFilme(@RequestBody Filme filmeRequest, @PathVariable long filmeId) {
        Optional<Filme> opt = filmeRepo.findById(filmeId);
        if (opt.isPresent()) {
            if (filmeRequest.getId() == filmeId) {
                filmeRepo.save(filmeRequest);
                return opt;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Erro ao alterar dados do filme com id " + filmeId);
    }

    @DeleteMapping(value = "/api/filmes/{id}")
    void deleteFilme(@PathVariable long id) {
        filmeRepo.deleteById(id);
    }
}
