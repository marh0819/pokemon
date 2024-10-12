package co.edu.uceva.pokemon.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.uceva.pokemon.persistence.entities.PokemonEntity;

@Repository
public interface PokemonRepository extends JpaRepository<PokemonEntity, Long>{

}
