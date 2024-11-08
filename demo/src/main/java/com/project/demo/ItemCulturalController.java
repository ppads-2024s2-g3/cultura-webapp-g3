package com.project.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/item-cultural")
public class ItemCulturalController {

    @Autowired
    private ItemCulturalRepository itemCulturalRepository;

    // Endpoint para listar todos os itens culturais
    @GetMapping
    public List<ItemCultural> getAllItems() {
        return itemCulturalRepository.findAll();
    }

    // Endpoint para adicionar um novo item cultural
    @PostMapping
    public ResponseEntity<ItemCultural> createItem(@RequestBody ItemCultural item) {
        ItemCultural newItem = itemCulturalRepository.save(item);
        return new ResponseEntity<>(newItem, HttpStatus.CREATED);
    }

    // Endpoint para buscar um item cultural pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<ItemCultural> getItemById(@PathVariable Long id) {
        return itemCulturalRepository.findById(id)
                .map(item -> new ResponseEntity<>(item, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint para atualizar um item cultural
    @PutMapping("/{id}")
    public ResponseEntity<ItemCultural> updateItem(@PathVariable Long id, @RequestBody ItemCultural updatedItem) {
        return itemCulturalRepository.findById(id)
                .map(item -> {
                    item.setNome(updatedItem.getNome());
                    item.setAnoLancamento(updatedItem.getAnoLancamento());
                    item.setGenero(updatedItem.getGenero());
                    ItemCultural savedItem = itemCulturalRepository.save(item);
                    return new ResponseEntity<>(savedItem, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint para remover um item cultural pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        if (itemCulturalRepository.existsById(id)) {
            itemCulturalRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
