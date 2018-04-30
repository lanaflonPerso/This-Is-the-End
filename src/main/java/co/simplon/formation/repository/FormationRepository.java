package co.simplon.formation.repository;

import co.simplon.formation.modele.Formation;
import co.simplon.formation.modele.Seance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FormationRepository extends JpaRepository<Formation, Long> {

}
