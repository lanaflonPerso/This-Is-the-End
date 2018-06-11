package co.simplon.formation.controleur;

import co.simplon.formation.model.Seance;
import co.simplon.formation.repository.SeanceRepository;
import co.simplon.formation.service.LettreConvocation;
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
    SeanceRepository service;

    @Autowired
    private LettreConvocation serviceConvocations;


    @GetMapping("/sessions")
    public List<Seance> getAll() {
        return service.findAll();
    }

    @GetMapping("/sessionsValides")
    public List<Seance> getValide() {
        return service.getValide();
    }

    @PostMapping("/session")
    public Seance create(@Valid @RequestBody Seance item) {
        return service.save(item);
    }

    @PostMapping("/convocations")
    public void convocation(@Valid @RequestBody Seance item) throws IOException, DocumentException {
        serviceConvocations.lettreConvocation(item);
    }

    @PostMapping("/emargement")
    public void emargement(@Valid @RequestBody Seance item) throws IOException, DocumentException {
        serviceConvocations.feuilleEmargement(item);
    }

    @GetMapping("/session/{id}")
    public ResponseEntity<Seance> getById(@PathVariable(value = "id") Long id) {
        Optional<Seance> item = service.findById(id);
        if (item.isPresent()) {
            Seance form = item.get();
            return ResponseEntity.ok().body(form);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/session/{id}")
    public Seance update(@PathVariable(value = "id") Long id,
                       @Valid @RequestBody Seance details) throws IOException, DocumentException {

        Optional<Seance> oldVersion = service.findById(id);
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
            if (details.getDateDebut() != null) {
                newVersion.setDateDebut(details.getDateDebut());
            }
            if (details.getDateFin() != null) {
                newVersion.setDateFin(details.getDateFin());
            }
            if (details.getValidation() != null) {
                newVersion.setValidation(details.getValidation());
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

            return service.save(newVersion);
        }
        return details;
    }

    @DeleteMapping("/session/{id}")
    public ResponseEntity<Seance> delete(@PathVariable(value = "id") Long id) {
        Optional<Seance> item = service.findById(id);
        if (item.isPresent()) {
            Seance form = item.get();
            service.deleteById(form.getId());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
