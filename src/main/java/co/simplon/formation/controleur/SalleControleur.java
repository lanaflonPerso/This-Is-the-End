package co.simplon.formation.controleur;


import co.simplon.formation.modele.Salle;
import co.simplon.formation.repository.SalleRepository;
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
    SalleRepository modelRepository;

    @GetMapping("/salles")
    public List<Salle> getAll() {
        return modelRepository.findAll();
    }

    @PostMapping("/salle")
    public Salle create(@Valid @RequestBody Salle item) {
        return modelRepository.save(item);
    }

    @GetMapping("/salle/{id}")
    public ResponseEntity<Salle> getById(@PathVariable(value = "id") Long id) {
        Optional<Salle> item = modelRepository.findById(id);
        if (item.isPresent()) {
            Salle form = item.get();
            return ResponseEntity.ok().body(form);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/salle/{id}")
    public Salle update(@PathVariable(value = "id") Long id,
                       @Valid @RequestBody Salle details) {

        Optional<Salle> oldVersion = modelRepository.findById(id);
        if (oldVersion.isPresent()) {
            Salle newVersion = oldVersion.get();
            if (details.getNom() != null) {
                newVersion.setNom(details.getNom());
            }
            return modelRepository.save(newVersion);
        }
        return details;
    }

    @DeleteMapping("/salle/{id}")
    public ResponseEntity<Salle> delete(@PathVariable(value = "id") Long id) {
        Optional<Salle> item = modelRepository.findById(id);
        if (item.isPresent()) {
            Salle form = item.get();
            modelRepository.delete(form);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
