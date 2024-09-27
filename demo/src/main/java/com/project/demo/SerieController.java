package com.project.demo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
class SerieController {

    @Autowired
    private SerieRepo serieRepo;

    @GetMapping("/api/series")
    Iterable<Serie> getSeries() {
        return serieRepo.findAll();
    }

    @GetMapping("/api/series/{id}")
    Optional<Serie> getSerie(@PathVariable long id) {
        return serieRepo.findById(id);
    }

    @PostMapping("/api/series")
    Serie createSerie(@RequestBody Serie serie) {
        return serieRepo.save(serie);
    }

    @PutMapping("/api/series/{serieId}")
    Optional<Serie> updateSerie(@RequestBody Serie serieRequest, @PathVariable long serieId) {
        Optional<Serie> opt = serieRepo.findById(serieId);
        if (opt.isPresent()) {
            if (serieRequest.getId() == serieId) {
                return Optional.of(serieRepo.save(serieRequest));
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Erro ao alterar dados da série com id " + serieId);
    }

    @DeleteMapping(value = "/api/series/{id}")
    void deleteSerie(@PathVariable long id) {
        serieRepo.deleteById(id);
    }
}
