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

import co.edu.uceva.pokemon.persistence.entities.PokemonHabilidadEntity;
import co.edu.uceva.pokemon.services.PokemonHabilidadService;

@RestController
@RequestMapping(path = "api/v1/pokemonHabilidad")
public class PokemonHabilidadController {

    private final PokemonHabilidadService pokemonHabilidadService;

    @Autowired
    public PokemonHabilidadController(PokemonHabilidadService pokemonHabilidadService) {
        this.pokemonHabilidadService = pokemonHabilidadService;
    }

    // Listar todas las relaciones pokemon-habilidad
    @GetMapping("/listar")
    public List<PokemonHabilidadEntity> getAll() {
        return pokemonHabilidadService.getAllPokemonHabilidades();
    }

    // Obtener una relación pokemon-habilidad por la clave compuesta (idPokemon, idHabilidad)
    @GetMapping("/{idPokemon}/{idHabilidad}")
    public Optional<PokemonHabilidadEntity> getByID(
        @PathVariable("idPokemon") int idPokemon, 
        @PathVariable("idHabilidad") int idHabilidad) {
        return pokemonHabilidadService.getPokemonHabilidad(idPokemon, idHabilidad);
    }

    // Guardar una nueva relación pokemon-habilidad
    @PostMapping("/guardar")
    public void save(@RequestBody PokemonHabilidadEntity pokemonHabilidad) {
        pokemonHabilidadService.save(pokemonHabilidad);
    }

    // Actualizar una relación pokemon-habilidad
    @PostMapping("/actualizar/{idPokemon}/{idHabilidad}")
    public void update(
        @PathVariable("idPokemon") int idPokemon,
        @PathVariable("idHabilidad") int idHabilidad,
        @RequestBody PokemonHabilidadEntity pokemonHabilidad) {
        pokemonHabilidadService.update(pokemonHabilidad);
    }

    // Eliminar una relación pokemon-habilidad por la clave compuesta
    @DeleteMapping("/{idPokemon}/{idHabilidad}")
    public void delete(
        @PathVariable("idPokemon") int idPokemon, 
        @PathVariable("idHabilidad") int idHabilidad) {
        pokemonHabilidadService.delete(idPokemon, idHabilidad);
    }
}
