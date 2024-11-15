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
    public ResponseEntity<Void> deleteAvaliacao(@PathVariable Long id) {
        // Verifica se a avaliação existe
        Avaliacao avaliacao = avaliacaoRepository.findById(id).orElse(null);
        if (avaliacao != null) {
            Geek geek = avaliacao.getGeek();
            
            // Excluir a avaliação
            avaliacaoRepository.delete(avaliacao);
    
            // Verifica se o Geek ainda tem avaliações associadas
            boolean geekHasAvaliacoes = avaliacaoRepository.existsByGeek(geek);
            if (!geekHasAvaliacoes) {
                // Se o Geek não tiver mais avaliações, pode deletar o Geek
                geekRepository.delete(geek);
            }
    
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }
}
