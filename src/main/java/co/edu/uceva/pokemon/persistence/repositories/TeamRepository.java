package co.edu.uceva.pokemon.persistence.repositories;

import co.edu.uceva.pokemon.persistence.entities.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<TeamEntity, Long> {
}
