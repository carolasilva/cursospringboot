package com.carolinaalves.cursospringboot.resources;

import com.carolinaalves.cursospringboot.domain.Cliente;
import com.carolinaalves.cursospringboot.dto.ClienteDto;
import com.carolinaalves.cursospringboot.dto.NovoClienteDto;
import com.carolinaalves.cursospringboot.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

  @Autowired
  private ClienteService service;

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ResponseEntity<Cliente> find(@PathVariable Integer id) {
    Cliente cliente = service.find(id);
    return ResponseEntity.ok().body(cliente);
  }

  @RequestMapping(method = RequestMethod.POST)
  public ResponseEntity<Void> insert(@Valid @RequestBody NovoClienteDto novoClienteDto) {
    Cliente cliente = service.fromDto(novoClienteDto);
    cliente = service.insert(cliente);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();

    return ResponseEntity.created(uri).build();
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public ResponseEntity<Void> update(@Valid @RequestBody ClienteDto clienteDto, @PathVariable Integer id) {
    Cliente cliente = service.fromDto(clienteDto);
    cliente.setId(id);
    service.update(cliente);
    return ResponseEntity.noContent().build();
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<Void> delete(@PathVariable Integer id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<List<ClienteDto>> findAll() {
    List<Cliente> clientes = service.findAll();
    List<ClienteDto> clientesDto = clientes.stream().map(obj -> new ClienteDto(obj)).collect(Collectors.toList());
    return ResponseEntity.ok().body(clientesDto);
  }

  @RequestMapping(value = "/page", method = RequestMethod.GET)
  public ResponseEntity<Page<ClienteDto>> findPage(
      @RequestParam(value = "page", defaultValue = "0") Integer page,
      @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
      @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
      @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
    Page<Cliente> clientes = service.findPage(page, linesPerPage, orderBy, direction);
    Page<ClienteDto> clientesDto = clientes.map(obj -> new ClienteDto(obj));
    return ResponseEntity.ok().body(clientesDto);
  }
}
