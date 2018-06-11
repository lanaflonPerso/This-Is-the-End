package co.simplon.formation.controleur;


import co.simplon.formation.model.Ilot;
import co.simplon.formation.service.IlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class IlotControleur {

    @Autowired
    private
    IlotService service;

    @GetMapping("/ilots")
    public List<Ilot> getAll() {
        return service.findAll();
    }

    @PostMapping("/ilot")
    public Ilot create(@Valid @RequestBody Ilot item) {
        return service.save(item);
    }

    @GetMapping("/ilot/{id}")
    public ResponseEntity<Ilot> getById(@PathVariable(value = "id") Long id) {
        Optional<Ilot> item = service.findById(id);
        if (item.isPresent()) {
            Ilot form = item.get();
            return ResponseEntity.ok().body(form);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/ilot/{id}")
    public Ilot update(@PathVariable(value = "id") Long id,
                       @Valid @RequestBody Ilot details) {

        Optional<Ilot> oldVersion = service.findById(id);
        if (oldVersion.isPresent()) {
            Ilot newVersion = oldVersion.get();
            if (details.getNom() != null) {
                newVersion.setNom(details.getNom());
            }
            return service.save(newVersion);
        }
        return details;
    }

    @DeleteMapping("/ilot/{id}")
    public ResponseEntity<Ilot> delete(@PathVariable(value = "id") Long id) {
        Optional<Ilot> item = service.findById(id);
        if (item.isPresent()) {
            Ilot form = item.get();
            service.delete(form);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
