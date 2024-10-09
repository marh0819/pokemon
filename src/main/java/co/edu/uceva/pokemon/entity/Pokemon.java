package co.edu.uceva.pokemon.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Pokemon")
public class Pokemon {
    private int idPokemon;
    private String nombre;
    private int idTipoPrimario;
    private int idTipoSecundario;
}
