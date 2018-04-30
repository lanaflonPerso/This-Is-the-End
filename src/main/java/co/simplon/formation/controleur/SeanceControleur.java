package co.simplon.formation.controleur;

import co.simplon.formation.modele.Seance;
import co.simplon.formation.repository.SeanceRepository;
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
public class SeanceControleur {

    @Autowired
    private
    SeanceRepository modelRepository;

    @GetMapping("/sessions")
    public List<Seance> getAll() {
        return modelRepository.findAll();
    }

    @PostMapping("/session")
    public Seance create(@Valid @RequestBody Seance item) {
        return modelRepository.save(item);
    }

    @GetMapping("/session/{id}")
    public ResponseEntity<Seance> getById(@PathVariable(value = "id") Long id) {
        Optional<Seance> item = modelRepository.findById(id);
        if (item.isPresent()) {
            Seance form = item.get();
            return ResponseEntity.ok().body(form);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/session/{id}")
    public Seance update(@PathVariable(value = "id") Long id,
                       @Valid @RequestBody Seance details) throws IOException, DocumentException {

        Optional<Seance> oldVersion = modelRepository.findById(id);
        if (oldVersion.isPresent()) {

            Seance newVersion = oldVersion.get();

            if (details.getNom() != null) {
                newVersion.setNom(details.getNom());
            }
            if (details.getAgent() != null) {
                newVersion.setAgent(details.getAgent());
            }
            if (details.getSalle() != null) {
                newVersion.setSalle(details.getSalle());
            }
            if (details.getHabilitation() != null) {
                newVersion.setHabilitation(details.getHabilitation());
            }
            if (details.getFormation() != null) {
                newVersion.setFormation(details.getFormation());
            }
            if (details.getFormateur() != null) {
                newVersion.setFormateur(details.getFormateur());
            }
            if (details.getSalle() != null) {
                newVersion.setSalle(details.getSalle());
            }
            if (details.getNbrePersonne() != null) {
                newVersion.setNbrePersonne(details.getNbrePersonne());
            }

            return modelRepository.save(newVersion);
        }
        return details;
    }

    @DeleteMapping("/session/{id}")
    public ResponseEntity<Seance> delete(@PathVariable(value = "id") Long id) {
        Optional<Seance> item = modelRepository.findById(id);
        if (item.isPresent()) {
            Seance form = item.get();
            modelRepository.delete(form);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
