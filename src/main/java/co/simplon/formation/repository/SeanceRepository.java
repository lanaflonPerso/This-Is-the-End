package co.simplon.formation.repository;

import co.simplon.formation.model.Seance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SeanceRepository extends JpaRepository<Seance, Long> {

    @Query("select u from Seance u where u.validation = true")
    List<Seance> getValide();

}
