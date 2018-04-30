package co.simplon.formation.controleur;

import co.simplon.formation.modele.Habilitation;
import co.simplon.formation.repository.HabilitationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class HabilitationControleur {

    @Autowired
    private
    HabilitationRepository modelRepository;

    @GetMapping("/habilitations")
    public List<Habilitation> getAll() {
        return modelRepository.findAll();
    }

    @PostMapping("/habilitation")
    public Habilitation create(@Valid @RequestBody Habilitation item) {
        return modelRepository.save(item);
    }

    @GetMapping("/habilitation/{id}")
    public ResponseEntity<Habilitation> getById(@PathVariable(value = "id") Long id) {
        Optional<Habilitation> item = modelRepository.findById(id);
        if (item.isPresent()) {
            Habilitation form = item.get();
            return ResponseEntity.ok().body(form);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/habilitation/{id}")
    public Habilitation update(@PathVariable(value = "id") Long id,
                       @Valid @RequestBody Habilitation details) {

        Optional<Habilitation> oldVersion = modelRepository.findById(id);
        if (oldVersion.isPresent()) {
            Habilitation newVersion = oldVersion.get();
            if (details.getNom() != null) {
                newVersion.setNom(details.getNom());
            }
            return modelRepository.save(newVersion);
        }
        return details;
    }

    @DeleteMapping("/habilitation/{id}")
    public ResponseEntity<Habilitation> delete(@PathVariable(value = "id") Long id) {
        Optional<Habilitation> item = modelRepository.findById(id);
        if (item.isPresent()) {
            Habilitation form = item.get();
            modelRepository.delete(form);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
