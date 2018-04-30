package co.simplon.formation.controleur;


import co.simplon.formation.modele.Grade;
import co.simplon.formation.outils.LettreConvocation;
import co.simplon.formation.repository.GradeRepository;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class GradeControleur {

    @Autowired
    private
    GradeRepository modelRepository;

    @GetMapping("/grades")
    public List<Grade> getAll() {
        return modelRepository.findAll();
    }

    @PostMapping("/grade")
    public Grade create(@Valid @RequestBody Grade item) {
        return modelRepository.save(item);
    }

    @GetMapping("/grade/{id}")
    public ResponseEntity<Grade> getById(@PathVariable(value = "id") Long id)  {
        Optional<Grade> item = modelRepository.findById(id);
        if (item.isPresent()) {
            Grade form = item.get();
            return ResponseEntity.ok().body(form);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/grade/{id}")
    public Grade update(@PathVariable(value = "id") Long id,
                       @Valid @RequestBody Grade details) {

        Optional<Grade> oldVersion = modelRepository.findById(id);
        if (oldVersion.isPresent()) {
            Grade newVersion = oldVersion.get();
            if (details.getClasse() != null) {
                newVersion.setClasse(details.getClasse());

            }
            return modelRepository.save(newVersion);
        }
        return details;
    }

    @DeleteMapping("/grade/{id}")
    public ResponseEntity<Grade> delete(@PathVariable(value = "id") Long id) {
        Optional<Grade> item = modelRepository.findById(id);
        if (item.isPresent()) {
            Grade form = item.get();
            modelRepository.delete(form);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
