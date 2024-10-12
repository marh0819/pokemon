package co.edu.uceva.pokemon.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uceva.pokemon.persistence.entities.PokemonEntity;
import co.edu.uceva.pokemon.services.PokemonService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "api/v1/pokemon")
public class PokemonController {

    private final PokemonService pokemonService;

    @Autowired
    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping("/listar")
    public List<PokemonEntity> getAll() {
        return pokemonService.getPokemon();
    }

    @GetMapping("/{idPokemon}")
    public ResponseEntity<PokemonEntity> getByID(@PathVariable("idPokemon") Long idPokemon) {
        return pokemonService.getPokemon(idPokemon.intValue())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/guardar")
    public ResponseEntity<Void> save(@Valid @RequestBody PokemonEntity pokemon) {
        pokemonService.save(pokemon);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/actualizar/{idPokemon}")
    public void update(@PathVariable Long idPokemon, @RequestBody PokemonEntity pokemon) {
        pokemon.setIdPokemon(idPokemon.intValue()); // Asegúrate de establecer el ID en el objeto pokemon
        pokemonService.update(pokemon);
    }

    @DeleteMapping("/{idPokemon}")
    public ResponseEntity<Void> delete(@PathVariable("idPokemon") Long idPokemon) {
        pokemonService.delete(idPokemon.intValue());
        return ResponseEntity.noContent().build();
    }
}

