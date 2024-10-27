package co.edu.uceva.pokemon.services.models.dtos;

public class PokemonDTO {
    private Integer pokedexNumber;
    private String name;

    public PokemonDTO(Integer pokedexNumber, String name) {
        this.pokedexNumber = pokedexNumber;
        this.name = name;
    }

    // Getters y setters
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

    @Override
    public String toString() {
        return "PokemonDTO{" +
                "pokedexNumber=" + pokedexNumber +
                ", name='" + name + '\'' +
                '}';
    }

}
