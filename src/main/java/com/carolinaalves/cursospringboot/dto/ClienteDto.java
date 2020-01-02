package com.carolinaalves.cursospringboot.dto;

import com.carolinaalves.cursospringboot.domain.Cliente;
import com.carolinaalves.cursospringboot.services.validation.ClienteUpdate;

import java.io.Serializable;

@ClienteUpdate
public class ClienteDto implements Serializable {
  private Integer id;
  private String nome;
  private String email;

  public ClienteDto() {
  }

  public ClienteDto(Cliente cliente) {
    id = cliente.getId();
    nome = cliente.getNome();
    email = cliente.getEmail();
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
