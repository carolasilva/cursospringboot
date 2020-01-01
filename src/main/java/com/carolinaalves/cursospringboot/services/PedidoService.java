package com.carolinaalves.cursospringboot.services;

import com.carolinaalves.cursospringboot.domain.Pedido;
import com.carolinaalves.cursospringboot.repository.PedidoRepository;
import com.carolinaalves.cursospringboot.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {
  @Autowired
  private PedidoRepository repository;

  public Pedido buscar(Integer id) {
    Optional<Pedido> obj = repository.findById(id);
    return obj.orElseThrow(() -> new ObjectNotFoundException(
        "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
  }
}
