package com.carolinaalves.cursospringboot.services;

import com.carolinaalves.cursospringboot.domain.Categoria;
import com.carolinaalves.cursospringboot.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {
  @Autowired
  private CategoriaRepository repository;

  public Categoria buscar(Integer id) {
    Optional<Categoria> obj = repository.findById(id);
    return obj.orElse(null);
  }
}
