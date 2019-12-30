package com.carolinaalves.cursospringboot.repository;

import com.carolinaalves.cursospringboot.domain.Cidade;
import com.carolinaalves.cursospringboot.domain.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {
}
