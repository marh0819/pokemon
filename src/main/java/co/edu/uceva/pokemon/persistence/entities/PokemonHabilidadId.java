package co.edu.uceva.pokemon.persistence.entities;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PokemonHabilidadId implements Serializable {

    private Integer idPokemon;
    private Integer idHabilidad;

    // Constructores, getters y setters

    public PokemonHabilidadId() {
    }

    public PokemonHabilidadId(Integer idPokemon, Integer idHabilidad) {
        this.idPokemon = idPokemon;
        this.idHabilidad = idHabilidad;
    }

    public Integer getIdPokemon() {
        return idPokemon;
    }

    public void setIdPokemon(Integer idPokemon) {
        this.idPokemon = idPokemon;
    }

    public Integer getIdHabilidad() {
        return idHabilidad;
    }

    public void setIdHabilidad(Integer idHabilidad) {
        this.idHabilidad = idHabilidad;
    }

    // Equals y hashCode (necesario para claves compuestas)

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PokemonHabilidadId that = (PokemonHabilidadId) o;
        return Objects.equals(idPokemon, that.idPokemon) && 
               Objects.equals(idHabilidad, that.idHabilidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPokemon, idHabilidad);
    }
}
