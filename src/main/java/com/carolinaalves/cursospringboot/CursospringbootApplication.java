package com.carolinaalves.cursospringboot;

import com.carolinaalves.cursospringboot.domain.Categoria;
import com.carolinaalves.cursospringboot.domain.Produto;
import com.carolinaalves.cursospringboot.repository.CategoriaRepository;
import com.carolinaalves.cursospringboot.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class CursospringbootApplication implements CommandLineRunner {

  @Autowired
  private CategoriaRepository categoriaRepository;

  @Autowired
  private ProdutoRepository produtoRepository;

  public static void main(String[] args) {
    SpringApplication.run(CursospringbootApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    Categoria cat1 = new Categoria(null, "Informática");
    Categoria cat2 = new Categoria(null, "Escritório");

    Produto p1 = new Produto(null, "Computador", 2000.0);
    Produto p2 = new Produto(null, "Impressora", 800.0);
    Produto p3 = new Produto(null, "Mouse", 80.0);

    cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
    cat2.getProdutos().add(p2);

    p1.getCategorias().add(cat1);
    p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
    p3.getCategorias().add(cat1);

    categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
    produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
  }
}
