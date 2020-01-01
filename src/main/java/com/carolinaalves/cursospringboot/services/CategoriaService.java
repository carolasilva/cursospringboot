package com.carolinaalves.cursospringboot.services;

import com.carolinaalves.cursospringboot.domain.Categoria;
import com.carolinaalves.cursospringboot.repository.CategoriaRepository;
import com.carolinaalves.cursospringboot.services.exceptions.DataIntegrityException;
import com.carolinaalves.cursospringboot.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
  @Autowired
  private CategoriaRepository repository;

  public Categoria find(Integer id) {
    Optional<Categoria> obj = repository.findById(id);
    return obj.orElseThrow(() -> new ObjectNotFoundException(
        "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
  }

  public Categoria insert(Categoria categoria) {
    categoria.setId(null);
    return repository.save(categoria);
  }

  public Categoria update(Categoria categoria) {
    find(categoria.getId());
    return repository.save(categoria);
  }

  public void delete(Integer id) {
    find(id);
    try {
      repository.deleteById(id);
    } catch(DataIntegrityViolationException e) {
      throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos vinculados");
    }
  }

  public List<Categoria> findAll() {
      return repository.findAll();
  }
}
