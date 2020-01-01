package com.carolinaalves.cursospringboot.dto;

import com.carolinaalves.cursospringboot.domain.Categoria;

import java.io.Serializable;

public class CategoriaDto implements Serializable {
  private Integer id;
  private String nome;

  public CategoriaDto() {
  }

  public CategoriaDto(Categoria categoria) {
    id = categoria.getId();
    nome = categoria.getNome();
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
}
