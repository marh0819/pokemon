package co.edu.uceva.pokemon.controllers;

import co.edu.uceva.pokemon.services.models.dtos.CreateTeamRequestDTO;
import co.edu.uceva.pokemon.services.models.dtos.PokemonDTO;
import co.edu.uceva.pokemon.services.models.dtos.TeamDTO;
import co.edu.uceva.pokemon.persistence.entities.PokemonEntity;
import co.edu.uceva.pokemon.persistence.entities.TeamEntity;
import co.edu.uceva.pokemon.services.TeamService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @PostMapping("/create/{userId}")
    public ResponseEntity<TeamDTO> createTeam(@PathVariable Long userId) {
        // Crear un DTO de solicitud con el userId y una lista vacía de Pokémon
        CreateTeamRequestDTO teamRequest = new CreateTeamRequestDTO(userId, new ArrayList<>(), null, null);

        // Llamar al método createTeam con el DTO
        TeamDTO createdTeam = teamService.createTeam(teamRequest);

        return new ResponseEntity<>(createdTeam, HttpStatus.CREATED);
    }

    @PostMapping("/{teamId}/addPokemon")
    public ResponseEntity<?> addPokemonToTeam(@PathVariable Long teamId,
            @RequestBody CreateTeamRequestDTO teamRequest) {
        System.out.println("Request JSON: PokedexNumber - " + teamRequest.getPokedexNumber() + ", Name - "
                + teamRequest.getName());
        try {
            // Crear PokemonDTO desde CreateTeamRequestDTO
            PokemonDTO pokemonDTO = new PokemonDTO(teamRequest.getPokedexNumber(), teamRequest.getName());
            System.out.println(
                    "Despues de: PokemonDTO pokemonDTO = new PokemonDTO(teamRequest.getPokedexNumber(), teamRequest.getName()); Converting to PokemonEntity: PokedexNumber - "
                            + pokemonDTO.getPokedexNumber() + ", Name - " + pokemonDTO.getName());

            // Llamar al servicio con el tipo correcto
            TeamDTO updatedTeam = teamService.addPokemonToTeam(teamId, pokemonDTO);
            System.out.println(
                    "Despues de: TeamDTO updatedTeam = teamService.addPokemonToTeam(teamId, pokemonDTO); Converting to PokemonEntity: PokedexNumber - "
                            + pokemonDTO.getPokedexNumber() + ", Name - " + pokemonDTO.getName());

            return new ResponseEntity<>(updatedTeam, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{teamId}")
    public ResponseEntity<TeamDTO> getTeam(@PathVariable Long teamId) {
        TeamDTO team = teamService.getTeamById(teamId);
        return new ResponseEntity<>(team, HttpStatus.OK);
    }

    @DeleteMapping("/{teamId}/removePokemon/{pokedexNumber}")
    public ResponseEntity<?> removePokemonFromTeam(
            @PathVariable Long teamId,
            @PathVariable Integer pokedexNumber) {
        try {
            teamService.removePokemonFromTeam(teamId, pokedexNumber);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
