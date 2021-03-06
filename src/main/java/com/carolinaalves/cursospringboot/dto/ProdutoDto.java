package com.carolinaalves.cursospringboot.dto;

import com.carolinaalves.cursospringboot.domain.Produto;

import java.io.Serializable;

public class ProdutoDto implements Serializable {
  private Integer id;
  private String nome;
  private Double preco;

  public ProdutoDto() {
  }

  public ProdutoDto(Produto produto) {
    id = produto.getId();
    nome = produto.getNome();
    preco = produto.getPreco();
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

  public Double getPreco() {
    return preco;
  }

  public void setPreco(Double preco) {
    this.preco = preco;
  }
}
