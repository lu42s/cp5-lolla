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

import br.fiap.cp1.lollapalooza.model.Palco;
import br.fiap.cp1.lollapalooza.repository.PalcoRepository;

@RestController
@RequestMapping("/palco")
public class PalcoController {
  @Autowired
    private PalcoRepository repository;

     @PostMapping
    public ResponseEntity<Palco> create(@RequestBody Palco palco) {         
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(palco));
    }

    @GetMapping("/{id}")    
    public ResponseEntity<Palco> findById(@PathVariable Long id) { 
        return repository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());        
    }

    @GetMapping    
    public ResponseEntity<List<Palco>> findAll() {        
        return ResponseEntity.ok(repository.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Palco> update(@PathVariable Long id, 
                                @RequestBody Palco palco) {

        Optional<Palco> optPalco = repository.findById(id);

        if (optPalco.isPresent()) {
            palco.setId(id);
            Palco palcoAlterado = repository.save(palco);
            return ResponseEntity.ok(palcoAlterado);
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
