package com.projeto.projeto.repository;

import com.projeto.projeto.entities.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
}

