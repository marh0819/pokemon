package co.edu.uceva.pokemon.services.models.dtos;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateTeamRequestDTO {
    private Long userId;
    private List<PokemonDTO> pokemons;

    @JsonProperty("pokedexNumber") // Esta anotación asegura el mapeo correcto
    private Integer pokedexNumber;

    @JsonProperty("name") // Esta anotación asegura el mapeo correcto
    private String name;

    // Constructor vacío para deserialización
    public CreateTeamRequestDTO() {
    }

    // Constructor completo
    public CreateTeamRequestDTO(Long userId, List<PokemonDTO> pokemons, Integer pokedexNumber, String name) {
        this.userId = userId;
        this.pokemons = pokemons;
        this.pokedexNumber = pokedexNumber;
        this.name = name;
    }

    // Getters y Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<PokemonDTO> getPokemons() {
        return pokemons;
    }

    public void setPokemons(List<PokemonDTO> pokemons) {
        this.pokemons = pokemons;
    }

    public Integer getPokedexNumber() {
        return pokedexNumber;
    }

    public void setPokedexNumber(Integer pokedexNumber) {
        this.pokedexNumber = pokedexNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}