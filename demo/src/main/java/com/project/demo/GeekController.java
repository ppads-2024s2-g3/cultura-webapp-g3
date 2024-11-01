package com.project.demo;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/geeks")
public class GeekController {
    private final GeekRepository geekRepository;

    public GeekController(GeekRepository geekRepository) {
        this.geekRepository = geekRepository;
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
    public ResponseEntity<Void> deleteGeek(@PathVariable Long id) {
        if (geekRepository.existsById(id)) {
            geekRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }
}
