package co.edu.uceva.pokemon.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uceva.pokemon.persistence.entities.PokemonEntity;
import co.edu.uceva.pokemon.services.PokemonService;

@RestController
@RequestMapping(path = "api/v1/pokemon")
public class PokemonController {

    private PokemonService pokemonService;

    @Autowired
    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping("/listar")
    public List<PokemonEntity> getAll() {
        return pokemonService.getPokemon();
    }

    @GetMapping("/{idPokemon}")
    public Optional<PokemonEntity> getByID(@PathVariable("idPokemon") int idPokemon) {
        return pokemonService.getPokemon(idPokemon);
    }

    @PostMapping("/guardar")
    public void save(@RequestBody PokemonEntity pokemon) {
        pokemonService.save(pokemon);
    }

    @PostMapping("/actualizar/{idPokemon}")
    public void update(@PathVariable Long idPokemon, @RequestBody PokemonEntity pokemon) {
        pokemonService.update(pokemon);
    }

    @DeleteMapping("/{idPokemon}")
    public void delete(@PathVariable("idPokemon") int idPokemon) {
        pokemonService.delete(idPokemon);
    }

}
