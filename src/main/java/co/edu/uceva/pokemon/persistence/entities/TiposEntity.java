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
@Table(name = "tipos")
public class TiposEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo") // Asegúrate de que el nombre sea correcto
    private Integer idTipo; // Cambiado a Integer para permitir null

    @Column(name = "nombre_tipo") // Opcional, pero buena práctica
    private String nombreTipo;
}