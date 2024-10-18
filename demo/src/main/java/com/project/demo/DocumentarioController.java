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
class DocumentarioController {

    @Autowired
    private DocumentarioRepo documentarioRepo;

    public DocumentarioController() {
    }

    @GetMapping("/api/documentarios")
    Iterable<Documentario> getDocumentarios(@RequestParam Optional<String> tema) {
        return documentarioRepo.findAll();
    }

    @GetMapping("/api/documentarios/{id}")
    Optional<Documentario> getDocumentario(@PathVariable long id) {
        return documentarioRepo.findById(id);
    }

    @PostMapping("/api/documentarios")
    Documentario createDocumentario(@RequestBody Documentario documentario) {
        return documentarioRepo.save(documentario);
    }

    @PutMapping("/api/documentarios/{documentarioId}")
    Optional<Documentario> updateDocumentario(@RequestBody Documentario documentarioRequest, @PathVariable long documentarioId) {
        Optional<Documentario> opt = documentarioRepo.findById(documentarioId);
        if (opt.isPresent()) {
            if (documentarioRequest.getNome().equals(opt.get().getNome())) {
                return Optional.of(documentarioRepo.save(documentarioRequest));
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Erro ao alterar dados do documentário com id " + documentarioId);
    }

    @DeleteMapping(value = "/api/documentarios/{id}")
    void deleteDocumentario(@PathVariable long id) {
        documentarioRepo.deleteById(id);
    }
}

