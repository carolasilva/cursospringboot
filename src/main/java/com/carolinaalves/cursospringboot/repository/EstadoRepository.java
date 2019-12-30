package com.carolinaalves.cursospringboot.repository;

import com.carolinaalves.cursospringboot.domain.Categoria;
import com.carolinaalves.cursospringboot.domain.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {
}
