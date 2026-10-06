package br.fiap.cp1.lollapalooza.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.fiap.cp1.lollapalooza.model.Dia;
import br.fiap.cp1.lollapalooza.repository.DiaRepository;

@RestController
@RequestMapping("/dia")
public class DiaController {
    
    @Autowired
    private DiaRepository repository;

     @PostMapping
    public ResponseEntity<Dia> create(@RequestBody Dia dia) {         
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(dia));
    }

    @GetMapping("/{id}")    
    public ResponseEntity<Dia> findById(@PathVariable Long id) { 
        return repository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());        
    }

    @GetMapping    
    public ResponseEntity<List<Dia>> findAll() {        
        return ResponseEntity.ok(repository.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dia> update(@PathVariable Long id, 
                                @RequestBody Dia dia) {

        Optional<Dia> optDia = repository.findById(id);

        if (optDia.isPresent()) {
            dia.setId(id);
            Dia diaAlterado = repository.save(dia);
            return ResponseEntity.ok(diaAlterado);
        } else {
            return ResponseEntity.notFound().build();
        }     
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) { 
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

