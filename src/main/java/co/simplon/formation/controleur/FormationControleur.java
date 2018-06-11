package co.simplon.formation.controleur;


import co.simplon.formation.model.Formation;
import co.simplon.formation.service.FormationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class FormationControleur {

    @Autowired
    private
    FormationService service;

    @GetMapping("/formations")
    public List<Formation> getAll() {
        return service.findAll();
    }

    @PostMapping("/formation")
    public Formation create(@Valid @RequestBody Formation item) {
        return service.save(item);
    }

    @GetMapping("/formation/{id}")
    public ResponseEntity<Formation> getById(@PathVariable(value = "id") Long id) {
        Optional<Formation> item = service.findById(id);
        if (item.isPresent()) {
            Formation form = item.get();
            return ResponseEntity.ok().body(form);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/formation/{id}")
    public Formation update(@PathVariable(value = "id") Long id,
                       @Valid @RequestBody Formation details) {

        Optional<Formation> oldVersion = service.findById(id);
        if (oldVersion.isPresent()) {
            Formation newVersion = oldVersion.get();
            if (details.getNom() != null) {
                newVersion.setNom(details.getNom());
            }
            if (details.getCode() != null) {
                newVersion.setCode(details.getCode());
            }
            if (details.getDuree() != null) {
                newVersion.setDuree(details.getDuree());
            }
            return service.save(newVersion);
        }
        return details;
    }

    @DeleteMapping("/formation/{id}")
    public ResponseEntity<Formation> delete(@PathVariable(value = "id") Long id) {
        Optional<Formation> item = service.findById(id);
        if (item.isPresent()) {
            Formation form = item.get();
            service.delete(form);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
