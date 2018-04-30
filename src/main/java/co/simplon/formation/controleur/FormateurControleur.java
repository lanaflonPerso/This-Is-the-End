package co.simplon.formation.controleur;


import co.simplon.formation.modele.Formateur;
import co.simplon.formation.repository.FormateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class FormateurControleur {

    @Autowired
    private
    FormateurRepository modelRepository;

    @GetMapping("/formateurs")
    public List<Formateur> getAll() {
        return modelRepository.findAll();
    }

    @PostMapping("/formateur")
    public Formateur create(@Valid @RequestBody Formateur item) {
        return modelRepository.save(item);
    }

    @GetMapping("/formateur/{id}")
    public ResponseEntity<Formateur> getById(@PathVariable(value = "id") Long id) {
        Optional<Formateur> item = modelRepository.findById(id);
        if (item.isPresent()) {
            Formateur form = item.get();
            return ResponseEntity.ok().body(form);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/formateur/{id}")
    public Formateur update(@PathVariable(value = "id") Long id,
                       @Valid @RequestBody Formateur details) {

        Optional<Formateur> oldVersion = modelRepository.findById(id);
        if (oldVersion.isPresent()) {
            Formateur newVersion = oldVersion.get();
            if (details.getNom() != null) {
                newVersion.setNom(details.getNom());
            }
            if (details.getPrenom() != null) {
                newVersion.setPrenom(details.getPrenom());
            }
            if (details.getIdRh() != null) {
                newVersion.setIdRh(details.getIdRh());
            }
            return modelRepository.save(newVersion);
        }
        return details;
    }

    @DeleteMapping("/formateur/{id}")
    public ResponseEntity<Formateur> delete(@PathVariable(value = "id") Long id) {
        Optional<Formateur> item = modelRepository.findById(id);
        if (item.isPresent()) {
            Formateur form = item.get();
            modelRepository.delete(form);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
