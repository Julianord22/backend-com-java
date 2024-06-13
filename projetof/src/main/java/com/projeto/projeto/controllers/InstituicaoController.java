package com.projeto.projeto.controllers;

import com.projeto.projeto.repository.InstituicaoRepository;
import com.projeto.projeto.entities.Instituicao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/instituicao")
public class InstituicaoController {
    @Autowired
    private InstituicaoRepository instituicaoRepository;

    @GetMapping
    public List<Instituicao> findAll() {
        return instituicaoRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Instituicao> findById(@PathVariable Long id) {
        Optional<Instituicao> instituicaoOptional = instituicaoRepository.findById(id);
        return instituicaoOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Instituicao> insert(@RequestBody Instituicao instituicao) {
        Instituicao novaInstituicao = instituicaoRepository.save(instituicao);
        return ResponseEntity.ok(novaInstituicao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Instituicao> update(@PathVariable Long id, @RequestBody Instituicao novoInstituicao) {
        Optional<Instituicao> instituicaoOptional = instituicaoRepository.findById(id);

        if (instituicaoOptional.isPresent()) {
            Instituicao instituicao = instituicaoOptional.get();
            instituicao.setNome(novoInstituicao.getNome());
            instituicao.setCnpj(novoInstituicao.getCnpj());
            instituicao.setEndereco(novoInstituicao.getEndereco());
            Instituicao resultado = instituicaoRepository.save(instituicao);
            return ResponseEntity.ok(resultado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<Instituicao> instituicaoOptional = instituicaoRepository.findById(id);

        if (instituicaoOptional.isPresent()) {
            instituicaoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
