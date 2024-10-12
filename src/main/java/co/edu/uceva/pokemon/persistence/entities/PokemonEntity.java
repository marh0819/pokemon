package co.edu.uceva.pokemon.persistence.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "Pokemon_Info")
public class PokemonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Pokemon")
    private Integer idPokemon;

    @Column(name = "nombre")
    private String nombre;

    @ManyToOne // Indica que es una relación muchos a uno
    @JoinColumn(name = "id_Tipo_Primario", referencedColumnName = "id_tipo") // Referencia a la clave primaria de TiposEntity
    private TiposEntity tipoPrimario;

    @ManyToOne // Indica que es una relación muchos a uno
    @JoinColumn(name = "id_Tipo_Secundario", referencedColumnName = "id_tipo") // Referencia a la clave primaria de TiposEntity
    private TiposEntity tipoSecundario;

    @OneToMany(mappedBy = "pokemon") // Relación inversa con PokemonHabilidadEntity
    private List<PokemonHabilidadEntity> habilidades; // Lista de habilidades asociadas
}
