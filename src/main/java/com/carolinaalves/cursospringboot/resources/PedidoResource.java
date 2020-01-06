package com.carolinaalves.cursospringboot.resources;

import com.carolinaalves.cursospringboot.domain.Pedido;
import com.carolinaalves.cursospringboot.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

  @Autowired
  private PedidoService service;

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ResponseEntity<Pedido> find(@PathVariable Integer id) {
    Pedido pedido = service.find(id);
    return ResponseEntity.ok().body(pedido);
  }

  @RequestMapping(method = RequestMethod.POST)
  public ResponseEntity<Void> insert(@Valid @RequestBody Pedido pedido) {
    pedido = service.insert(pedido);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pedido.getId()).toUri();

    return ResponseEntity.created(uri).build();
  }
}
