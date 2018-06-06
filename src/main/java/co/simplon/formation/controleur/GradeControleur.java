package co.simplon.formation.controleur;


import co.simplon.formation.modele.Grade;
import co.simplon.formation.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class GradeControleur {

    @Autowired
    private
    GradeService service;

    @GetMapping("/grades")
    public List<Grade> getAll() {
        return service.findAll();
    }

    @PostMapping("/grade")
    public Grade create(@Valid @RequestBody Grade item) {
        return service.save(item);
    }

    @GetMapping("/grade/{id}")
    public ResponseEntity<Grade> getById(@PathVariable(value = "id") Long id)  {
        Optional<Grade> item = service.findById(id);
        if (item.isPresent()) {
            Grade form = item.get();
            return ResponseEntity.ok().body(form);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/grade/{id}")
    public Grade update(@PathVariable(value = "id") Long id,
                       @Valid @RequestBody Grade details) {

        Optional<Grade> oldVersion = service.findById(id);
        if (oldVersion.isPresent()) {
            Grade newVersion = oldVersion.get();
            if (details.getClasse() != null) {
                newVersion.setClasse(details.getClasse());
            }
            if (details.getGrade() != null) {
                newVersion.setGrade(details.getGrade());
            }
            return service.save(newVersion);
        }
        return details;
    }

    @DeleteMapping("/grade/{id}")
    public ResponseEntity<Grade> delete(@PathVariable(value = "id") Long id) {
        Optional<Grade> item = service.findById(id);
        if (item.isPresent()) {
            Grade form = item.get();
            service.delete(form);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
