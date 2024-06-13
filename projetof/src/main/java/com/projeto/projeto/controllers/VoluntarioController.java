package com.projeto.projeto.controllers;

import com.projeto.projeto.entities.Voluntario;
import com.projeto.projeto.repository.VoluntarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/voluntarios")
public class VoluntarioController {

    @Autowired
    private VoluntarioRepository voluntarioRepository;

    @GetMapping
    public ResponseEntity<List<Voluntario>> getAllVoluntarios() {
        List<Voluntario> voluntarios = voluntarioRepository.findAll();
        return ResponseEntity.ok(voluntarios);
    }

    @PostMapping
    public ResponseEntity<Voluntario> createVoluntario(@Valid @RequestBody Voluntario voluntario) {
        Voluntario novoVoluntario = voluntarioRepository.save(voluntario);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoVoluntario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Voluntario> getVoluntarioById(@PathVariable Long id) {
        Optional<Voluntario> voluntarioOptional = voluntarioRepository.findById(id);
        return voluntarioOptional.map(ResponseEntity::ok)
                                  .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Voluntario> updateVoluntario(@PathVariable Long id, @Valid @RequestBody Voluntario voluntarioDetails) {
        Optional<Voluntario> voluntarioOptional = voluntarioRepository.findById(id);
        if (voluntarioOptional.isPresent()) {
            Voluntario voluntario = voluntarioOptional.get();
            voluntario.setNome(voluntarioDetails.getNome());
            voluntario.setEmail(voluntarioDetails.getEmail());
            voluntario.setDisponibilidade(voluntarioDetails.getDisponibilidade());
            voluntario.setHabilidade(voluntarioDetails.getHabilidade());
            Voluntario voluntarioAtualizado = voluntarioRepository.save(voluntario);
            return ResponseEntity.ok(voluntarioAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVoluntario(@PathVariable Long id) {
        Optional<Voluntario> voluntarioOptional = voluntarioRepository.findById(id);
        if (voluntarioOptional.isPresent()) {
            voluntarioRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/habilidade/{habilidade}")
    public ResponseEntity<List<Voluntario>> getVoluntariosByHabilidade(@PathVariable String habilidade) {
        List<Voluntario> voluntarios = voluntarioRepository.findByHabilidade(habilidade);
        return ResponseEntity.ok(voluntarios);
    }

    @GetMapping("/disponibilidade/{disponibilidade}")
    public ResponseEntity<List<Voluntario>> getVoluntariosByDisponibilidade(@PathVariable String disponibilidade) {
        List<Voluntario> voluntarios = voluntarioRepository.findByDisponibilidade(disponibilidade);
        return ResponseEntity.ok(voluntarios);
    }

}
