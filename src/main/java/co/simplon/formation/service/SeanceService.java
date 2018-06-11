package co.simplon.formation.service;


import co.simplon.formation.model.Seance;
import co.simplon.formation.repository.SeanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeanceService  {

    @Autowired
    SeanceRepository repo;

    public List<Seance> findAll() {
        return repo.findAll();
    }

    public List<Seance> getValide() {
        return repo.getValide();
    }

    public Optional<Seance> findById(Long id) {
        return repo.findById(id);
    }

    public Seance save(Seance item) {
        return repo.save(item);
    }

    public void delete(Seance item) {
        repo.delete(item);
    }



}
