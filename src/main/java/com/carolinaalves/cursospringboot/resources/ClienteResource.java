package com.carolinaalves.cursospringboot.resources;

import com.carolinaalves.cursospringboot.domain.Cliente;
import com.carolinaalves.cursospringboot.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

  @Autowired
  private ClienteService service;

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ResponseEntity<?> find(@PathVariable Integer id) {
    Cliente cliente = service.buscar(id);
    return ResponseEntity.ok().body(cliente);
  }
}