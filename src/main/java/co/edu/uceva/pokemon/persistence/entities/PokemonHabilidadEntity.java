package co.edu.uceva.pokemon.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "pokemon_habilidad")
public class PokemonHabilidadEntity {

    @EmbeddedId
    private PokemonHabilidadId id; // Clave compuesta

    @Column(name = "habilidad_oculta")
    private Boolean habilidadOculta;

    // Constructores, getters y setters

    public PokemonHabilidadEntity() {
    }

    public PokemonHabilidadEntity(PokemonHabilidadId id, Boolean habilidadOculta) {
        this.id = id;
        this.habilidadOculta = habilidadOculta;
    }

    public PokemonHabilidadId getId() {
        return id;
    }

    public void setId(PokemonHabilidadId id) {
        this.id = id;
    }

    public Boolean getHabilidadOculta() {
        return habilidadOculta;
    }

    public void setHabilidadOculta(Boolean habilidadOculta) {
        this.habilidadOculta = habilidadOculta;
    }
}
