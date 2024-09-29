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
class DesenhoAnimadoController {

    @Autowired
    private DesenhoAnimadoRepo desenhoAnimadoRepo;

    public DesenhoAnimadoController() {
    }

    @GetMapping("/api/desenhos")
    Iterable<DesenhoAnimado> getDesenhos(@RequestParam Optional<Long> algumId) {
        return desenhoAnimadoRepo.findAll();
    }

    @GetMapping("/api/desenhos/{id}")
    Optional<DesenhoAnimado> getDesenho(@PathVariable long id) {
        return desenhoAnimadoRepo.findById(id);
    }

    @PostMapping("/api/desenhos")
    DesenhoAnimado createDesenho(@RequestBody DesenhoAnimado desenho) {
        return desenhoAnimadoRepo.save(desenho);
    }

    @PutMapping("/api/desenhos/{desenhoId}")
    Optional<DesenhoAnimado> updateDesenho(@RequestBody DesenhoAnimado desenhoRequest, @PathVariable long desenhoId) {
        Optional<DesenhoAnimado> opt = desenhoAnimadoRepo.findById(desenhoId);
        if (opt.isPresent()) {
            if (desenhoRequest.getId() == desenhoId) {
                desenhoAnimadoRepo.save(desenhoRequest);
                return opt;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Erro ao alterar dados do desenho animado com id " + desenhoId);
    }

    @DeleteMapping(value = "/api/desenhos/{id}")
    void deleteDesenho(@PathVariable long id) {
        desenhoAnimadoRepo.deleteById(id);
    }
}
