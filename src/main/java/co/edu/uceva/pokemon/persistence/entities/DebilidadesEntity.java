package co.edu.uceva.pokemon.persistence.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "debilidades")
@IdClass(DebilidadesId.class)
public class DebilidadesEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_tipo", referencedColumnName = "id_tipo")
    private TiposEntity idTipo;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_tipo_debil", referencedColumnName = "id_tipo")
    private TiposEntity idTipoDebil;

}
