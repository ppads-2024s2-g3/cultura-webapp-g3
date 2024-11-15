package com.project.demo;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

    private final AvaliacaoRepository avaliacaoRepository;
    private final GeekRepository geekRepository;
    private final ItemCulturalRepository itemCulturalRepository;

    // Injeção das dependências
    public AvaliacaoController(AvaliacaoRepository avaliacaoRepository, 
                               GeekRepository geekRepository, 
                               ItemCulturalRepository itemCulturalRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
        this.geekRepository = geekRepository;
        this.itemCulturalRepository = itemCulturalRepository;
    }

    // Método para criar avaliação com verificação de Geek e ItemCultural
    @PostMapping
    @Transactional
    public ResponseEntity<?> createAvaliacao(@RequestBody Avaliacao avaliacao) {
        try {
            // Verificar se o Geek existe
            Geek geek = geekRepository.findById(avaliacao.getGeek().getId()).orElse(null);
            // Verificar se o ItemCultural existe
            ItemCultural itemCultural = itemCulturalRepository.findById(avaliacao.getItemCultural().getId()).orElse(null);

            // Se Geek ou ItemCultural não foram encontrados, retornar erro
            if (geek == null || itemCultural == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Geek ou Item Cultural não encontrados");
            }

            // Associar Geek e ItemCultural à Avaliação
            avaliacao.setGeek(geek);
            avaliacao.setItemCultural(itemCultural);

            // Salvar a avaliação
            Avaliacao savedAvaliacao = avaliacaoRepository.save(avaliacao);

            return ResponseEntity.status(HttpStatus.CREATED).body(savedAvaliacao);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    // Método para obter todas as avaliações
    @GetMapping
    public List<Avaliacao> getAllAvaliacoes() {
        return avaliacaoRepository.findAll();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> deleteAvaliacao(@PathVariable Long id) {
        try {
            // Verifica se a avaliação existe
            Avaliacao avaliacao = avaliacaoRepository.findById(id).orElse(null);
            if (avaliacao != null) {
                Geek geek = avaliacao.getGeek();
                ItemCultural itemCultural = avaliacao.getItemCultural();
                
                // Desvincula o Geek e o ItemCultural da avaliação
                avaliacao.setGeek(null);
                avaliacao.setItemCultural(null);
                
                // Salva a avaliação com as referências desvinculadas
                avaliacaoRepository.save(avaliacao);
    
                // Excluir a avaliação
                avaliacaoRepository.delete(avaliacao);
    
                // Verifica se o Geek ainda tem avaliações associadas
                boolean geekHasAvaliacoes = avaliacaoRepository.existsByGeek(geek);
                
                if (!geekHasAvaliacoes) {
                    // Se o Geek não tiver mais avaliações, pode deletar o Geek
                    geekRepository.delete(geek);
                }
    
                // Verifica se o ItemCultural ainda está associado a alguma avaliação
                boolean itemCulturalHasAvaliacoes = avaliacaoRepository.existsByItemCultural(itemCultural);
                
                if (!itemCulturalHasAvaliacoes) {
                    // Se o ItemCultural não tiver mais avaliações, pode deletar o ItemCultural
                    itemCulturalRepository.delete(itemCultural);
                }
    
                return ResponseEntity.noContent().build(); // 204 No Content
            } else {
                return ResponseEntity.notFound().build(); // 404 Not Found
            }
        } catch (Exception e) {
            // Log detalhado do erro para facilitar o diagnóstico
            System.err.println("Erro ao deletar avaliação: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar avaliação");
        }
    }
}