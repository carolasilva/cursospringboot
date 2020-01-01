package com.carolinaalves.cursospringboot.resources;

import com.carolinaalves.cursospringboot.domain.Categoria;
import com.carolinaalves.cursospringboot.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

  @Autowired
  private CategoriaService service;

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ResponseEntity<Categoria> find(@PathVariable Integer id) {
    Categoria categoria = service.find(id);
    return ResponseEntity.ok().body(categoria);
  }

  @RequestMapping(method = RequestMethod.POST)
  public ResponseEntity<Void> insert(@RequestBody Categoria categoria) {
    categoria = service.insert(categoria);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getId()).toUri();

    return ResponseEntity.created(uri).build();
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public ResponseEntity<Void> update(@RequestBody Categoria categoria, @PathVariable Integer id) {
    categoria.setId(id);
    categoria = service.update(categoria);
    return ResponseEntity.noContent().build();
  }
}
