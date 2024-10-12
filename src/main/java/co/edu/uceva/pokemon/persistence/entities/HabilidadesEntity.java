package co.edu.uceva.pokemon.persistence.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "habilidades")
public class HabilidadesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_habilidades")
    private Integer idHabilidades;

    @Column(name = "nombre_habilidad")
    private String nombreHabilidad;

    @Column(name = "descripcion")
    private Integer descripcion;

    @OneToMany(mappedBy = "habilidad") // Relación inversa
    private List<PokemonHabilidadEntity> pokemonHabilidades; // Opcional
}
