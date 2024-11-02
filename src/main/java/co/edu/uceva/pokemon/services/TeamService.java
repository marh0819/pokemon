package co.edu.uceva.pokemon.services;

import co.edu.uceva.pokemon.services.models.dtos.CreateTeamRequestDTO;
import co.edu.uceva.pokemon.services.models.dtos.PokemonDTO;
import co.edu.uceva.pokemon.services.models.dtos.TeamDTO;
import co.edu.uceva.pokemon.persistence.entities.TeamEntity;
import co.edu.uceva.pokemon.persistence.entities.PokemonEntity;

import java.util.List;

public interface TeamService {
    TeamDTO createTeam(CreateTeamRequestDTO teamRequest);
    TeamDTO getTeamById(Long teamId);
    TeamDTO addPokemonToTeam(Long teamId, PokemonDTO pokemonDTO);
    TeamDTO removePokemonFromTeam(Long teamId, Integer pokedexNumber);
}