package co.simplon.formation.service;


import co.simplon.formation.modele.Seance;
import co.simplon.formation.repository.SeanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface SeanceService extends SeanceRepository  {


}
