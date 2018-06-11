package co.simplon.formation.controleur;


import co.simplon.formation.model.Salle;
import co.simplon.formation.service.SalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class SalleControleur {

    @Autowired
    private
    SalleService service;

    @GetMapping("/salles")
    public List<Salle> getAll() {
        return service.findAll();
    }

    @PostMapping("/salle")
    public Salle create(@Valid @RequestBody Salle item) {
        return service.save(item);
    }

    @GetMapping("/salle/{id}")
    public ResponseEntity<Salle> getById(@PathVariable(value = "id") Long id) {
        Optional<Salle> item = service.findById(id);
        if (item.isPresent()) {
            Salle form = item.get();
            return ResponseEntity.ok().body(form);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/salle/{id}")
    public Salle update(@PathVariable(value = "id") Long id,
                       @Valid @RequestBody Salle details) {

        Optional<Salle> oldVersion = service.findById(id);
        if (oldVersion.isPresent()) {
            Salle newVersion = oldVersion.get();
            if (details.getNom() != null) {
                newVersion.setNom(details.getNom());
            }
            if (details.getEtage() != null) {
                newVersion.setEtage(details.getEtage());
            }
            return service.save(newVersion);
        }
        return details;
    }

    @DeleteMapping("/salle/{id}")
    public ResponseEntity<Salle> delete(@PathVariable(value = "id") Long id) {
        Optional<Salle> item = service.findById(id);
        if (item.isPresent()) {
            Salle form = item.get();
            service.delete(form);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
