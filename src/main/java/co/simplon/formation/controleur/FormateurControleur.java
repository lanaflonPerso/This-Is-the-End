package co.simplon.formation.controleur;


import co.simplon.formation.model.Formateur;
import co.simplon.formation.service.FormateurService;
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
    FormateurService service;

    @GetMapping("/formateurs")
    public List<Formateur> getAll() {
        return service.findAll();
    }

    @PostMapping("/formateur")
    public Formateur create(@Valid @RequestBody Formateur item) {
        return service.save(item);
    }

    @GetMapping("/formateur/{id}")
    public ResponseEntity<Formateur> getById(@PathVariable(value = "id") Long id) {
        Optional<Formateur> item = service.findById(id);
        if (item.isPresent()) {
            Formateur form = item.get();
            return ResponseEntity.ok().body(form);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/formateur/{id}")
    public Formateur update(@PathVariable(value = "id") Long id,
                       @Valid @RequestBody Formateur details) {

        Optional<Formateur> oldVersion = service.findById(id);
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
            if (details.getMail() != null) {
                newVersion.setMail(details.getMail());
            }
            if (details.getTel() != null) {
                newVersion.setTel(details.getTel());
            }
            return service.save(newVersion);
        }
        return details;
    }

    @DeleteMapping("/formateur/{id}")
    public ResponseEntity<Formateur> delete(@PathVariable(value = "id") Long id) {
        Optional<Formateur> item = service.findById(id);
        if (item.isPresent()) {
            Formateur form = item.get();
            service.delete(form);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
