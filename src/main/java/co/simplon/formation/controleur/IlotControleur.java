package co.simplon.formation.controleur;


import co.simplon.formation.modele.Ilot;
import co.simplon.formation.repository.IlotRepository;
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
    IlotRepository modelRepository;

    @GetMapping("/ilots")
    public List<Ilot> getAll() {
        return modelRepository.findAll();
    }

    @PostMapping("/ilot")
    public Ilot create(@Valid @RequestBody Ilot item) {
        return modelRepository.save(item);
    }

    @GetMapping("/ilot/{id}")
    public ResponseEntity<Ilot> getById(@PathVariable(value = "id") Long id) {
        Optional<Ilot> item = modelRepository.findById(id);
        if (item.isPresent()) {
            Ilot form = item.get();
            return ResponseEntity.ok().body(form);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/ilot/{id}")
    public Ilot update(@PathVariable(value = "id") Long id,
                       @Valid @RequestBody Ilot details) {

        Optional<Ilot> oldVersion = modelRepository.findById(id);
        if (oldVersion.isPresent()) {
            Ilot newVersion = oldVersion.get();
            if (details.getNom() != null) {
                newVersion.setNom(details.getNom());
            }
            return modelRepository.save(newVersion);
        }
        return details;
    }

    @DeleteMapping("/ilot/{id}")
    public ResponseEntity<Ilot> delete(@PathVariable(value = "id") Long id) {
        Optional<Ilot> item = modelRepository.findById(id);
        if (item.isPresent()) {
            Ilot form = item.get();
            modelRepository.delete(form);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
