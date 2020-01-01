package com.carolinaalves.cursospringboot.services;

import com.carolinaalves.cursospringboot.domain.Cidade;
import com.carolinaalves.cursospringboot.domain.Cliente;
import com.carolinaalves.cursospringboot.domain.Endereco;
import com.carolinaalves.cursospringboot.domain.enums.TipoCliente;
import com.carolinaalves.cursospringboot.dto.ClienteDto;
import com.carolinaalves.cursospringboot.dto.NovoClienteDto;
import com.carolinaalves.cursospringboot.repository.ClienteRepository;
import com.carolinaalves.cursospringboot.repository.EnderecoRepository;
import com.carolinaalves.cursospringboot.services.exceptions.DataIntegrityException;
import com.carolinaalves.cursospringboot.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
  @Autowired
  private ClienteRepository repository;

  @Autowired
  private EnderecoRepository enderecoRepository;

  public Cliente find(Integer id) {
    Optional<Cliente> obj = repository.findById(id);
    return obj.orElseThrow(() -> new ObjectNotFoundException(
        "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
  }

  public Cliente insert(Cliente cliente) {
    cliente.setId(null);
    cliente = repository.save(cliente);
    enderecoRepository.saveAll(cliente.getEnderecos());
    return cliente;
  }

  public Cliente update(Cliente cliente) {
    Cliente novoCliente = find(cliente.getId());
    updateDados(novoCliente, cliente);
    return repository.save(novoCliente);
  }

  private void updateDados(Cliente novoCliente, Cliente cliente) {
    novoCliente.setNome(cliente.getNome());
    novoCliente.setEmail(cliente.getEmail());
  }

  public void delete(Integer id) {
    find(id);
    try {
      repository.deleteById(id);
    } catch(DataIntegrityViolationException e) {
      throw new DataIntegrityException("Não é possível excluir um cliente que possui pedidos vinculados");
    }
  }

  public List<Cliente> findAll() {
    return repository.findAll();
  }

  public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
    PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
    return repository.findAll(pageRequest);
  }

  public Cliente fromDto(ClienteDto clienteDto){
    return new Cliente(clienteDto.getId(), clienteDto.getNome(), clienteDto.getEmail(), null, null);
  }

  public Cliente fromDto(NovoClienteDto clienteDto){
    Cliente cliente = new Cliente(null, clienteDto.getNome(), clienteDto.getEmail(), clienteDto.getCpfOuCnpj(),
       TipoCliente.toEnum(clienteDto.getTipo()));
    Cidade cidade = new Cidade(clienteDto.getCidadeId(), null, null);
    Endereco endereco = new Endereco(null, clienteDto.getLogradouro(), clienteDto.getNumero(), clienteDto.getComplemento(),
        clienteDto.getBairro(), clienteDto.getCep(), cliente, cidade);
    cliente.getEnderecos().add(endereco);
    cliente.getTelefones().add(clienteDto.getTelefone1());
    if (clienteDto.getTelefone2() != null)
      cliente.getTelefones().add(clienteDto.getTelefone2());
    if (clienteDto.getTelefone3() != null)
      cliente.getTelefones().add(clienteDto.getTelefone3());

    return cliente;
  }
}

