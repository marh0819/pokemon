package co.edu.uceva.pokemon.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uceva.pokemon.persistence.entities.PokemonEntity;
import co.edu.uceva.pokemon.persistence.repositories.PokemonRepository;

@Service
public class PokemonService {
    private final PokemonRepository pokemonRepository;

    @Autowired
    public PokemonService(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    public List<PokemonEntity> getPokemon() {
        return pokemonRepository.findAll();
    }

    public Optional<PokemonEntity> getPokemon(int id){
        return pokemonRepository.findById(Long.valueOf (id));
    }

    public void save(PokemonEntity pokemon){
        pokemonRepository.save(pokemon);
    }

    public void update(PokemonEntity pokemon){
        pokemonRepository.save(pokemon);
    }

    public void delete(int id){
        pokemonRepository.deleteById(Long.valueOf (id));
    }
}
