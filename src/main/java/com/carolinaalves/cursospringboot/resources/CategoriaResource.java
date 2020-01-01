package com.carolinaalves.cursospringboot.resources;

import com.carolinaalves.cursospringboot.domain.Categoria;
import com.carolinaalves.cursospringboot.dto.CategoriaDto;
import com.carolinaalves.cursospringboot.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<Void> delete(@PathVariable Integer id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<List<CategoriaDto>> findAll() {
    List<Categoria> categorias = service.findAll();
    List<CategoriaDto> categoriasDto = categorias.stream().map(obj -> new CategoriaDto(obj)).collect(Collectors.toList());
    return ResponseEntity.ok().body(categoriasDto);
  }

  @RequestMapping(value = "/page", method = RequestMethod.GET)
  public ResponseEntity<Page<CategoriaDto>> findPage(
      @RequestParam(value = "page", defaultValue = "0") Integer page,
      @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
      @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
      @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
    Page<Categoria> categorias = service.findPage(page, linesPerPage, orderBy, direction);
    Page<CategoriaDto> categoriasDto = categorias.map(obj -> new CategoriaDto(obj));
    return ResponseEntity.ok().body(categoriasDto);
  }

}
