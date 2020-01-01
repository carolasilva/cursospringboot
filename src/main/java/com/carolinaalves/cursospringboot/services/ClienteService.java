package com.carolinaalves.cursospringboot.services;

import com.carolinaalves.cursospringboot.domain.Cliente;
import com.carolinaalves.cursospringboot.repository.ClienteRepository;
import com.carolinaalves.cursospringboot.repository.ClienteRepository;
import com.carolinaalves.cursospringboot.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {
  @Autowired
  private ClienteRepository repository;

  public Cliente buscar(Integer id) {
    Optional<Cliente> obj = repository.findById(id);
    return obj.orElseThrow(() -> new ObjectNotFoundException(
        "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
  }
}
