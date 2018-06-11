package co.simplon.formation.repository;

import co.simplon.formation.model.Ilot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IlotRepository extends JpaRepository<Ilot, Long> {


}
