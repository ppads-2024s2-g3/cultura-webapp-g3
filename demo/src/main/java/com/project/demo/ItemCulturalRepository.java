package com.project.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCulturalRepository extends JpaRepository<ItemCultural, Long> {
}
