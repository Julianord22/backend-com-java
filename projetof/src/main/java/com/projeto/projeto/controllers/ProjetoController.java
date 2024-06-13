package com.projeto.projeto.controllers;

import com.projeto.projeto.entities.Projeto;
import com.projeto.projeto.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/projetos")
public class ProjetoController {

    @Autowired
    private ProjetoRepository projetoRepository;

    @GetMapping
    public ResponseEntity<List<Projeto>> getAllProjetos() {
        List<Projeto> projetos = projetoRepository.findAll();
        return ResponseEntity.ok(projetos);
    }

    @PostMapping
    public ResponseEntity<Projeto> createProjeto(@Valid @RequestBody Projeto projeto) {
        Projeto novoProjeto = projetoRepository.save(projeto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoProjeto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Projeto> getProjetoById(@PathVariable Long id) {
        Optional<Projeto> projetoOptional = projetoRepository.findById(id);
        return projetoOptional.map(ResponseEntity::ok)
                               .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Projeto> updateProjeto(@PathVariable Long id, @Valid @RequestBody Projeto projetoDetails) {
        Optional<Projeto> projetoOptional = projetoRepository.findById(id);
        if (projetoOptional.isPresent()) {
            Projeto projeto = projetoOptional.get();
            projeto.setNome(projetoDetails.getNome());
            projeto.setDescricao(projetoDetails.getDescricao());
            projeto.setStatus(projetoDetails.getStatus());
            Projeto projetoAtualizado = projetoRepository.save(projeto);
            return ResponseEntity.ok(projetoAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProjeto(@PathVariable Long id) {
        Optional<Projeto> projetoOptional = projetoRepository.findById(id);
        if (projetoOptional.isPresent()) {
            projetoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
