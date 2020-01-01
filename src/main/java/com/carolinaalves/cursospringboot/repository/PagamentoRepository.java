package com.carolinaalves.cursospringboot.repository;

import com.carolinaalves.cursospringboot.domain.Pagamento;
import com.carolinaalves.cursospringboot.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {
}
