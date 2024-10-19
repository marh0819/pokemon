package co.edu.uceva.pokemon.persistence.entities;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
@Embeddable
public class DebilidadesId implements Serializable {

    private Integer idTipo;
    private Integer idTipoDebil;

    // Constructores, getters y setters

    public DebilidadesId() {
    }

    public DebilidadesId(Integer idTipo, Integer idTipoDebil) {
        this.idTipo = idTipo;
        this.idTipoDebil = idTipoDebil;
    }

    // Equals y hashCode (necesario para claves compuestas)

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        DebilidadesId that = (DebilidadesId) o;
        return Objects.equals(idTipo, that.idTipo) &&
                Objects.equals(idTipoDebil, that.idTipoDebil);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTipo, idTipoDebil);
    }
}
