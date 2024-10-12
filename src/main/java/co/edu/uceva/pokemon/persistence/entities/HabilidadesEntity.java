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
@Table(name = "habilidades")
public class HabilidadesEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_habilidades") // Asegúrate de que el nombre sea correcto
    private Integer idHabilidades; // Cambiado a Integer para permitir null

    @Column(name = "nombre_habilidad") // Opcional, pero buena práctica
    private String nombreHabilidad;

    @Column(name = "descripcion") // Opcional, pero buena práctica
    private Integer descripcion;
}
