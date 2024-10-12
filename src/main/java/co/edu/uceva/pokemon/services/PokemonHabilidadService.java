package co.edu.uceva.pokemon.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uceva.pokemon.persistence.entities.PokemonHabilidadEntity;
import co.edu.uceva.pokemon.persistence.entities.PokemonHabilidadId;
import co.edu.uceva.pokemon.persistence.repositories.PokemonHabilidadRepository;

@Service
public class PokemonHabilidadService {

    private final PokemonHabilidadRepository pokemonHabilidadRepository;

    @Autowired
    public PokemonHabilidadService(PokemonHabilidadRepository pokemonHabilidadRepository) {
        this.pokemonHabilidadRepository = pokemonHabilidadRepository;
    }

    public List<PokemonHabilidadEntity> getAllPokemonHabilidades() {
        return pokemonHabilidadRepository.findAll();
    }

    public Optional<PokemonHabilidadEntity> getPokemonHabilidad(int idPokemon, int idHabilidad) {
        PokemonHabilidadId pokemonHabilidadId = new PokemonHabilidadId(idPokemon, idHabilidad);
        return pokemonHabilidadRepository.findById(pokemonHabilidadId);
    }

    public void save(PokemonHabilidadEntity pokemonHabilidad) {
        pokemonHabilidadRepository.save(pokemonHabilidad);
    }

    public void update(PokemonHabilidadEntity pokemonHabilidad) {
        save(pokemonHabilidad); // Puedes reutilizar el método save
    }

    public void delete(int idPokemon, int idHabilidad) {
        PokemonHabilidadId pokemonHabilidadId = new PokemonHabilidadId(idPokemon, idHabilidad);
        pokemonHabilidadRepository.deleteById(pokemonHabilidadId);
    }
}
