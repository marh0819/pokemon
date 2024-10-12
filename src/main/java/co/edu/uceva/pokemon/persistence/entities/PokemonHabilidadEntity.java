package co.edu.uceva.pokemon.persistence.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "pokemon_habilidad")
public class PokemonHabilidadEntity {

    @EmbeddedId
    private PokemonHabilidadId id; // Clave compuesta

    @Column(name = "habilidad_oculta")
    private Boolean habilidadOculta;

    // Relación con PokemonEntity
    @ManyToOne
    @MapsId("idPokemon") // Mapea la parte de la clave compuesta
    @JoinColumn(name = "id_pokemon", referencedColumnName = "id_Pokemon") // Referencia a la clave primaria de PokemonEntity
    private PokemonEntity pokemon;

    // Relación con HabilidadesEntity
    @ManyToOne
    @MapsId("idHabilidad") // Mapea la parte de la clave compuesta
    @JoinColumn(name = "id_habilidad", referencedColumnName = "id_habilidades") // Referencia a la clave primaria de HabilidadesEntity
    private HabilidadesEntity habilidad;

    // Constructores
    public PokemonHabilidadEntity() {
    }

    public PokemonHabilidadEntity(PokemonHabilidadId id, Boolean habilidadOculta, PokemonEntity pokemon, HabilidadesEntity habilidad) {
        this.id = id;
        this.habilidadOculta = habilidadOculta;
        this.pokemon = pokemon;
        this.habilidad = habilidad;
    }

    // Getters y Setters
}
