package com.projeto.projeto.repository;

import com.projeto.projeto.entities.Voluntario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoluntarioRepository extends JpaRepository<Voluntario, Long> {
    List<Voluntario> findByHabilidade(String habilidade);
    List<Voluntario> findByDisponibilidade(String disponibilidade);
}
