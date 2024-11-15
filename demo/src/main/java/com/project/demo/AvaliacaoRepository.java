package com.project.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    List<Avaliacao> findByGeekId(Long geekId); // Método para encontrar avaliações por Geek ID

    long countByGeekId(Long id);

    boolean existsByGeek(Geek geek);

	boolean existsByItemCultural(ItemCultural itemCultural);

    List<Avaliacao> findByGeek(Geek geek);
}

