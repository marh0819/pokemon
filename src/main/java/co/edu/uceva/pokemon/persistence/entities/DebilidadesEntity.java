package co.edu.uceva.pokemon.persistence.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "debilidades")
public class DebilidadesEntity {

    @EmbeddedId
    private DebilidadesId id; // Clave compuesta

    @Column(name = "efectividad")
    private BigDecimal efectividad; // Cambiado a BigDecimal para permitir decimales

    // Relación con TiposEntity para id_tipo
    @ManyToOne
    @MapsId("idTipo") // Mapea la parte de la clave compuesta
    @JoinColumn(name = "id_tipo", referencedColumnName = "id_tipo") // Referencia a la clave primaria de TiposEntity
    private TiposEntity tipo;

    // Relación con TiposEntity para id_tipo_debil
    @ManyToOne
    @MapsId("idTipoDebil") // Mapea la parte de la clave compuesta
    @JoinColumn(name = "id_tipo_debil", referencedColumnName = "id_tipo") // Referencia a la clave primaria de
                                                                          // TiposEntity
    private TiposEntity tipoDebil;

    // Constructores
    public DebilidadesEntity() {
    }

    public DebilidadesEntity(DebilidadesId id, TiposEntity tipo, TiposEntity tipoDebil, BigDecimal efectividad) {
        this.id = id;
        this.tipo = tipo;
        this.tipoDebil = tipoDebil;
        this.efectividad = efectividad; // Añadido parámetro para efectividad
    }
}
