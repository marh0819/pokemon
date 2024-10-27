package co.edu.uceva.pokemon.persistence.repositories;

import co.edu.uceva.pokemon.persistence.entities.PokemonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonRepository extends JpaRepository<PokemonEntity, Long> {
}
