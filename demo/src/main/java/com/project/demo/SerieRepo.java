package com.project.demo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.*;

public interface SerieRepo extends CrudRepository<Serie, Long> {

    Iterable<Serie> findAll();

    Optional<Serie> findById(long id);

    void deleteById(long id);
}
