package co.edu.uceva.pokemon.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uceva.pokemon.entity.Pokemon;
import co.edu.uceva.pokemon.repository.PokemonRepository;

@Service
public class PokemonService {
    private final PokemonRepository pokemonRepository;

    @Autowired
    public PokemonService(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    public List<Pokemon> getPokemon() {
        return pokemonRepository.findAll();
    }

    public Optional<Pokemon> getPokemon(int id){
        return pokemonRepository.findById(Long.valueOf (id));
    }

    public void save(Pokemon pokemon){
        pokemonRepository.save(pokemon);
    }

    public void update(Pokemon pokemon){
        pokemonRepository.save(pokemon);
    }

    public void delete(int id){
        pokemonRepository.deleteById(Long.valueOf (id));
    }
}
