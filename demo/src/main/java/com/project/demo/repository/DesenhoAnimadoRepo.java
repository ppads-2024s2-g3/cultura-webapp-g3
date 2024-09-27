package com.project.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.project.demo.entidade.DesenhoAnimado;

public interface DesenhoAnimadoRepo extends CrudRepository<DesenhoAnimado, Long> {
}
