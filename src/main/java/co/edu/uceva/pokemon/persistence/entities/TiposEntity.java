package co.edu.uceva.pokemon.persistence.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tipos")
public class TiposEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo") // Asegúrate de que el nombre sea correcto
    private Long idTipo; // Cambiado a Integer para permitir null

    @Column(name = "nombre_tipo") // Opcional, pero buena práctica
    private String nombreTipo;

}
