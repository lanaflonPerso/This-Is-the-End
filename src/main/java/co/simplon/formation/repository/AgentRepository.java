package co.simplon.formation.repository;

import co.simplon.formation.modele.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {

    List<Agent> findByNom(String nom);

    @Query("select u from Agent u")
    List<Agent> tout();


}


