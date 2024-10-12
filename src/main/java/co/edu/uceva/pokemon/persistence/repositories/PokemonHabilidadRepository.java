package co.edu.uceva.pokemon.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.uceva.pokemon.persistence.entities.PokemonHabilidadEntity;
import co.edu.uceva.pokemon.persistence.entities.PokemonHabilidadId;

public interface PokemonHabilidadRepository extends JpaRepository<PokemonHabilidadEntity, PokemonHabilidadId> {
}
