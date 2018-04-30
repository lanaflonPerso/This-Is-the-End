package co.simplon.formation.controleur;


import co.simplon.formation.modele.Agent;
import co.simplon.formation.repository.AgentRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class AgentControleur {

    @Autowired
    private
    AgentRepository modelRepository;

    @GetMapping("/agents")
    public List<Agent> getAll() {
        return modelRepository.tout();
    }

    @PostMapping("/agent")
    public Agent create(@Valid @RequestBody Agent item) {
        item.setNom(item.getNom().toUpperCase());
        item.setPrenom(StringUtils.capitalize(item.getPrenom().toLowerCase()));
        item.setIdRh(item.getIdRh().toUpperCase());
        return modelRepository.save(item);
    }

    @GetMapping("/agent/{id}")
    public ResponseEntity<Agent> getById(@PathVariable(value = "id") Long id) {
        Optional<Agent> item = modelRepository.findById(id);
        if (item.isPresent()) {
            Agent form = item.get();

            return ResponseEntity.ok().body(form);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/agent/{id}")
    public Agent update(@PathVariable(value = "id") Long id,
                       @Valid @RequestBody Agent details) {

        Optional<Agent> oldVersion = modelRepository.findById(id);
        if (oldVersion.isPresent()) {
            Agent newVersion = oldVersion.get();
            if (details.getNom() != null) {
                newVersion.setNom(details.getNom().toUpperCase());
            }
            if (details.getPrenom() != null) {
                newVersion.setPrenom(StringUtils.capitalize(details.getPrenom().toLowerCase()));
            }
            if (details.getDateNaissance() != null) {
                newVersion.setDateNaissance(details.getDateNaissance());
            }
            if (details.getIdRh() != null) {
                newVersion.setIdRh(details.getIdRh().toUpperCase());
            }
            if (details.getGrade() != null) {
                newVersion.setGrade(details.getGrade());
            }
            if (details.getIlot() != null) {
                newVersion.setIlot(details.getIlot());
            }
            if (details.getFlux() != null) {
                newVersion.setFlux(details.getFlux());
            }
            if (details.getSeance() != null) {
                newVersion.setSeance(details.getSeance());
            }

            return modelRepository.save(newVersion);
        }
        return details;
    }

    @DeleteMapping("/agent/{id}")
    public ResponseEntity<Agent> delete(@PathVariable(value = "id") Long id) {
        Optional<Agent> item = modelRepository.findById(id);
        if (item.isPresent()) {
            Agent form = item.get();
            modelRepository.delete(form);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
