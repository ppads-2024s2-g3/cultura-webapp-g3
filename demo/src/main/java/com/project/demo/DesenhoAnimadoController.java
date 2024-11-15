package com.project.demo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.server.ResponseStatusException;

@RestController
class DesenhoAnimadoController {

    @Autowired
    private DesenhoAnimadoRepo desenhoAnimadoRepo; // Repositório específico para DesenhoAnimado

    public DesenhoAnimadoController() {
    }

    @GetMapping("/api/desenhos")
    Iterable<DesenhoAnimado> getDesenhos(@RequestParam Optional<Long> algumId) {
        return desenhoAnimadoRepo.findAll(); // Busca todos os desenhos animados
    }


    @GetMapping("/api/desenhos/{id}")
    Optional<DesenhoAnimado> getDesenho(@PathVariable long id) {
        return desenhoAnimadoRepo.findById(id); // Busca desenho animado pelo ID
    }

    @PostMapping("/api/desenhos")
    DesenhoAnimado createDesenho(@RequestBody DesenhoAnimado desenho) {
        return desenhoAnimadoRepo.save(desenho); // Cria um novo desenho animado
    }

    @PutMapping("/api/desenhos/{desenhoId}")
    Optional<DesenhoAnimado> updateDesenho(@RequestBody DesenhoAnimado desenhoRequest, @PathVariable long desenhoId) {
        Optional<DesenhoAnimado> opt = desenhoAnimadoRepo.findById(desenhoId);
        if (opt.isPresent()) {
            if (desenhoRequest.getId() == desenhoId) {
                desenhoAnimadoRepo.save(desenhoRequest); // Atualiza o desenho animado
                return opt;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Erro ao alterar dados do desenho animado com id " + desenhoId);
    }

    @DeleteMapping(value = "/api/desenhos/{id}")
    void deleteDesenho(@PathVariable long id) {
        desenhoAnimadoRepo.deleteById(id); // Deleta o desenho animado
    }
}
