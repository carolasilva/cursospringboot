package com.carolinaalves.cursospringboot.repository;

import com.carolinaalves.cursospringboot.domain.ItemPedido;
import com.carolinaalves.cursospringboot.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {
}
