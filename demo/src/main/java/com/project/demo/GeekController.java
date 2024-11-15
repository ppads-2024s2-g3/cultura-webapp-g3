package com.project.demo;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/geeks")
public class GeekController {
    private final GeekRepository geekRepository;
    private final AvaliacaoRepository avaliacaoRepository; // Repositório de avaliações


    public GeekController(GeekRepository geekRepository, AvaliacaoRepository avaliacaoRepository) {
        this.geekRepository = geekRepository;
        this.avaliacaoRepository = avaliacaoRepository;
    }

    @PostMapping
    public ResponseEntity<Geek> createGeek(@RequestBody Geek geek) {
        Geek savedGeek = geekRepository.save(geek);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedGeek);
    }

    @GetMapping
    public List<Geek> getAllGeeks() {
        return geekRepository.findAll();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteGeek(@PathVariable Long id) {
        // Verifica se o Geek existe
        Geek geek = geekRepository.findById(id).orElse(null);
        if (geek != null) {
            // Exclui todas as avaliações associadas ao Geek
            List<Avaliacao> avaliacoes = avaliacaoRepository.findByGeek(geek);
            for (Avaliacao avaliacao : avaliacoes) {
                avaliacaoRepository.delete(avaliacao);
            }
            
            // Agora pode excluir o Geek
            geekRepository.delete(geek);
            
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }
}