package co.simplon.formation.repository;

import co.simplon.formation.modele.Flux;
import co.simplon.formation.modele.Ilot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IlotRepository extends JpaRepository<Ilot, Long> {


}
