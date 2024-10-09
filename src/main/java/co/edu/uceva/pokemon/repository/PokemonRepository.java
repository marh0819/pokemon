package co.edu.uceva.pokemon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.uceva.pokemon.entity.Pokemon;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Long>{

}
