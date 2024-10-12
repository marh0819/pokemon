package co.edu.uceva.pokemon.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Pokemon_Info")
public class PokemonEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Pokemon") // Asegúrate de que el nombre sea correcto
    private Integer idPokemon; // Cambiado a Integer para permitir null

    @Column(name = "nombre") // Opcional, pero buena práctica
    private String nombre;

    @Column(name = "id_TipoPrimario") // Opcional, pero buena práctica
    private Integer idTipoPrimario;

    @Column(name = "id_TipoSecundario") // Opcional, pero buena práctica
    private Integer idTipoSecundario;
}
