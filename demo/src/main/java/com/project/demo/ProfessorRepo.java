package com.project.demo;

import org.springframework.data.repository.CrudRepository;

public interface ProfessorRepo extends CrudRepository<Professor, Long> {
}