package co.edu.uceva.pokemon.services.impl;

import co.edu.uceva.pokemon.services.models.dtos.CreateTeamRequestDTO;
import co.edu.uceva.pokemon.services.models.dtos.PokemonDTO;
import co.edu.uceva.pokemon.services.models.dtos.TeamDTO;
import jakarta.transaction.Transactional;
import co.edu.uceva.pokemon.persistence.entities.PokemonEntity;
import co.edu.uceva.pokemon.persistence.entities.TeamEntity;
import co.edu.uceva.pokemon.persistence.entities.UserEntity;
import co.edu.uceva.pokemon.persistence.repositories.TeamRepository;
import co.edu.uceva.pokemon.persistence.repositories.UserRepository;
import co.edu.uceva.pokemon.persistence.repositories.PokemonRepository;
import co.edu.uceva.pokemon.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uceva.pokemon.persistence.repositories.PokemonRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements TeamService {

        @Autowired
        private TeamRepository teamRepository;

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private PokemonRepository pokemonRepository; // Inyección de PokemonRepository

        @Override
        public TeamDTO createTeam(CreateTeamRequestDTO teamRequest) {
                // Buscar usuario o lanzar excepción
                UserEntity user = userRepository.findById(teamRequest.getUserId())
                                .orElseThrow(() -> new RuntimeException("User not found"));

                // Crear y asociar equipo
                TeamEntity team = new TeamEntity();
                team.setUser(user);

                // Si hay Pokémon en la solicitud, añadirlos al equipo
                if (teamRequest.getPokemons() != null) {
                        List<PokemonEntity> pokemons = teamRequest.getPokemons().stream()
                                        .map(pokemonDTO -> new PokemonEntity(pokemonDTO.getPokedexNumber(),
                                                        pokemonDTO.getName(), team))
                                        .collect(Collectors.toList());
                        team.setPokemons(pokemons);
                }

                // Guardar equipo en el repositorio
                TeamEntity savedTeam = teamRepository.save(team);

                // Convertir equipo a TeamDTO
                List<PokemonDTO> pokemonDTOs = savedTeam.getPokemons().stream()
                                .map(pokemon -> new PokemonDTO(pokemon.getPokedexNumber(), pokemon.getName()))
                                .collect(Collectors.toList());

                return new TeamDTO(savedTeam.getId(), savedTeam.getUser().getId(), pokemonDTOs);
        }

        @Override
        public TeamDTO getTeamById(Long teamId) {
                // Buscar equipo y lanzar excepción si no existe
                TeamEntity team = teamRepository.findById(teamId)
                                .orElseThrow(() -> new RuntimeException("Team not found"));

                // Convertir lista de Pokémon a PokemonDTO
                List<PokemonDTO> pokemons = team.getPokemons().stream()
                                .map(pokemon -> new PokemonDTO(pokemon.getPokedexNumber(), pokemon.getName()))
                                .collect(Collectors.toList());

                return new TeamDTO(team.getId(), team.getUser().getId(), pokemons);
        }

        @Transactional
        @Override
        public TeamDTO addPokemonToTeam(Long teamId, PokemonDTO pokemonDTO) {
                // Buscar el equipo existente
                TeamEntity team = teamRepository.findById(teamId)
                                .orElseThrow(() -> new RuntimeException("Team not found"));

                // Verifica que el equipo tenga menos de 6 Pokémon
                if (team.getPokemons().size() >= 6) {
                        throw new RuntimeException("The team cannot have more than 6 Pokémon");
                }

                // Crear el objeto PokemonEntity y guardarlo explícitamente
                PokemonEntity pokemon = new PokemonEntity(pokemonDTO.getPokedexNumber(), pokemonDTO.getName(), team);
                pokemon = pokemonRepository.save(pokemon); // Guardar el PokemonEntity
                team.getPokemons().add(pokemon);

                // Guarda el equipo actualizado en la base de datos
                teamRepository.save(team);

                // Convertir a TeamDTO para la respuesta
                List<PokemonDTO> pokemonDTOs = team.getPokemons().stream()
                                .map(p -> new PokemonDTO(p.getPokedexNumber(), p.getName()))
                                .collect(Collectors.toList());

                return new TeamDTO(team.getId(), team.getUser().getId(), pokemonDTOs);
        }

        @Transactional
        @Override
        public TeamDTO removePokemonFromTeam(Long teamId, Integer pokedexNumber) {
                // Buscar el equipo
                TeamEntity team = teamRepository.findById(teamId)
                                .orElseThrow(() -> new RuntimeException("Team not found"));

                // Buscar el Pokémon a eliminar
                PokemonEntity pokemon = team.getPokemons().stream()
                                .filter(p -> p.getPokedexNumber().equals(pokedexNumber))
                                .findFirst()
                                .orElseThrow(() -> new RuntimeException("Pokemon not found in team"));

                // Eliminar el Pokémon del equipo
                team.getPokemons().remove(pokemon);
                pokemonRepository.delete(pokemon); // Eliminar el Pokémon de la base de datos

                // Crear la lista actualizada de PokemonDTO
                List<PokemonDTO> updatedPokemonList = team.getPokemons().stream()
                                .map(p -> new PokemonDTO(p.getPokedexNumber(), p.getName()))
                                .collect(Collectors.toList());

                // Devolver el TeamDTO con la lista actualizada
                return new TeamDTO(team.getId(), team.getUser().getId(), updatedPokemonList);
        }

}