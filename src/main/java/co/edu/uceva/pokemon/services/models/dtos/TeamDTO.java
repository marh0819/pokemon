package co.edu.uceva.pokemon.services.models.dtos;

import java.util.List;

public class TeamDTO {
    private Long id;
    private Long userId;
    private List<PokemonDTO> pokemons;

    // Constructor
    public TeamDTO(Long id, Long userId, List<PokemonDTO> pokemons) {
        this.id = id;
        this.userId = userId;
        this.pokemons = pokemons;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "TeamDTO{" +
                "id=" + id +
                ", userId=" + userId +
                ", pokemons=" + pokemons +
                '}';
    }

}
