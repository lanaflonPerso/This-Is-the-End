package co.simplon.formation.controleur;

import co.simplon.formation.model.Flux;
import co.simplon.formation.service.FluxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class FluxControleur {

    @Autowired
    private
    FluxService service;

    @GetMapping("/flux")
    public List<Flux> getAll() {
        return service.findAll();
    }

    @PostMapping("/flux")
    public Flux create(@Valid @RequestBody Flux item) {
        return service.save(item);
    }

    @GetMapping("/flux/{id}")
    public ResponseEntity<Flux> getById(@PathVariable(value = "id") Long id) {
        Optional<Flux> item = service.findById(id);
        if (item.isPresent()) {
            Flux form = item.get();
            return ResponseEntity.ok().body(form);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/flux/{id}")
    public Flux update(@PathVariable(value = "id") Long id,
                       @Valid @RequestBody Flux details) {

        Optional<Flux> oldVersion = service.findById(id);
        if (oldVersion.isPresent()) {
            Flux newVersion = oldVersion.get();
            if (details.getNom() != null) {
                newVersion.setNom(details.getNom());
            }
            return service.save(newVersion);
        }
        return details;
    }

    @DeleteMapping("/flux/{id}")
    public ResponseEntity<Flux> delete(@PathVariable(value = "id") Long id) {
        Optional<Flux> item = service.findById(id);
        if (item.isPresent()) {
            Flux form = item.get();
            service.delete(form);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
