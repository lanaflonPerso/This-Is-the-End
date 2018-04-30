package co.simplon.formation.repository;

import co.simplon.formation.modele.Flux;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FluxRepository extends JpaRepository<Flux, Long> {


}
