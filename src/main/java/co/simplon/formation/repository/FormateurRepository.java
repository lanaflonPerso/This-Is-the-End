package co.simplon.formation.repository;

import co.simplon.formation.model.Formateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FormateurRepository extends JpaRepository<Formateur, Long> {

}
