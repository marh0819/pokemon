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

import co.edu.uceva.pokemon.persistence.entities.PokemonHabilidadEntity;
import co.edu.uceva.pokemon.services.PokemonHabilidadService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "api/v1/pokemonHabilidad")
public class PokemonHabilidadController {

    private final PokemonHabilidadService pokemonHabilidadService;

    @Autowired
    public PokemonHabilidadController(PokemonHabilidadService pokemonHabilidadService) {
        this.pokemonHabilidadService = pokemonHabilidadService;
    }

    @GetMapping("/listar")
    public List<PokemonHabilidadEntity> getAll() {
        return pokemonHabilidadService.getAllPokemonHabilidades();
    }

    @GetMapping("/{idPokemon}/{idHabilidad}")
    public ResponseEntity<PokemonHabilidadEntity> getByID(@PathVariable("idPokemon") int idPokemon, 
                                                          @PathVariable("idHabilidad") int idHabilidad) {
        return pokemonHabilidadService.getPokemonHabilidad(idPokemon, idHabilidad)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/guardar")
    public ResponseEntity<Void> save(@Valid @RequestBody PokemonHabilidadEntity pokemonHabilidad) {
        pokemonHabilidadService.save(pokemonHabilidad);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/actualizar/{idPokemon}/{idHabilidad}")
    public void update(
        @PathVariable("idPokemon") int idPokemon,
        @PathVariable("idHabilidad") int idHabilidad,
        @RequestBody PokemonHabilidadEntity pokemonHabilidad) {
        pokemonHabilidadService.update(pokemonHabilidad);
    }

    @DeleteMapping("/{idPokemon}/{idHabilidad}")
    public ResponseEntity<Void> delete(@PathVariable("idPokemon") int idPokemon, 
                                        @PathVariable("idHabilidad") int idHabilidad) {
        pokemonHabilidadService.delete(idPokemon, idHabilidad);
        return ResponseEntity.noContent().build();
    }
}
