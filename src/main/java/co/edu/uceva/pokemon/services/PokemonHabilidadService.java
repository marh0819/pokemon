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

    // Obtener todas las relaciones pokemon-habilidad
    public List<PokemonHabilidadEntity> getAllPokemonHabilidades() {
        return pokemonHabilidadRepository.findAll();
    }

    // Obtener una relación específica por id compuesta
    public Optional<PokemonHabilidadEntity> getPokemonHabilidad(int idPokemon, int idHabilidad) {
        PokemonHabilidadId pokemonHabilidadId = new PokemonHabilidadId(idPokemon, idHabilidad);
        return pokemonHabilidadRepository.findById(pokemonHabilidadId);
    }

    // Guardar una nueva relación pokemon-habilidad
    public void save(PokemonHabilidadEntity pokemonHabilidad) {
        pokemonHabilidadRepository.save(pokemonHabilidad);
    }

    // Actualizar una relación pokemon-habilidad
    public void update(PokemonHabilidadEntity pokemonHabilidad) {
        pokemonHabilidadRepository.save(pokemonHabilidad);
    }

    // Eliminar una relación pokemon-habilidad por id compuesta
    public void delete(int idPokemon, int idHabilidad) {
        PokemonHabilidadId pokemonHabilidadId = new PokemonHabilidadId(idPokemon, idHabilidad);
        pokemonHabilidadRepository.deleteById(pokemonHabilidadId);
    }
}
