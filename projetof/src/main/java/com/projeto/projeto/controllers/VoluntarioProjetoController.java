package com.projeto.projeto.controllers;

import com.projeto.projeto.entities.VoluntarioProjeto;
import com.projeto.projeto.repository.VoluntarioProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/voluntario-projetos")
public class VoluntarioProjetoController {

    @Autowired
    private VoluntarioProjetoRepository voluntarioProjetoRepository;

    @GetMapping
    public ResponseEntity<List<VoluntarioProjeto>> getAllVoluntarioProjetos() {
        List<VoluntarioProjeto> voluntarioProjetos = voluntarioProjetoRepository.findAll();
        return ResponseEntity.ok(voluntarioProjetos);
    }

    @PostMapping
    public ResponseEntity<VoluntarioProjeto> createVoluntarioProjeto(@Valid @RequestBody VoluntarioProjeto voluntarioProjeto) {
        VoluntarioProjeto novoVoluntarioProjeto = voluntarioProjetoRepository.save(voluntarioProjeto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoVoluntarioProjeto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VoluntarioProjeto> getVoluntarioProjetoById(@PathVariable Long id) {
        Optional<VoluntarioProjeto> voluntarioProjetoOptional = voluntarioProjetoRepository.findById(id);
        return voluntarioProjetoOptional.map(ResponseEntity::ok)
                                        .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<VoluntarioProjeto> updateVoluntarioProjeto(@PathVariable Long id, @Valid @RequestBody VoluntarioProjeto voluntarioProjetoDetails) {
        Optional<VoluntarioProjeto> voluntarioProjetoOptional = voluntarioProjetoRepository.findById(id);
        if (voluntarioProjetoOptional.isPresent()) {
            VoluntarioProjeto voluntarioProjeto = voluntarioProjetoOptional.get();
            voluntarioProjeto.setProjeto_id(voluntarioProjetoDetails.getProjeto_id());
            voluntarioProjeto.setVoluntario_id(voluntarioProjetoDetails.getVoluntario_id());
            voluntarioProjeto.setDataInicio(voluntarioProjetoDetails.getDataInicio());
            voluntarioProjeto.setDataFim(voluntarioProjetoDetails.getDataFim());
            VoluntarioProjeto voluntarioProjetoAtualizado = voluntarioProjetoRepository.save(voluntarioProjeto);
            return ResponseEntity.ok(voluntarioProjetoAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVoluntarioProjeto(@PathVariable Long id) {
        Optional<VoluntarioProjeto> voluntarioProjetoOptional = voluntarioProjetoRepository.findById(id);
        if (voluntarioProjetoOptional.isPresent()) {
            voluntarioProjetoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
