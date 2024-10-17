package co.edu.uceva.pokemon.persistence.entities;

import lombok.Data;
import java.io.Serializable;
import java.util.Objects;

@Data
public class DebilidadesId implements Serializable {

    private Long tipo;
    private Long tipoDebil;

    // Constructor por defecto, equals y hashCode
    public DebilidadesId() {
    }

    public DebilidadesId(Long tipo, Long tipoDebil) {
        this.tipo = tipo;
        this.tipoDebil = tipoDebil;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        DebilidadesId that = (DebilidadesId) o;
        return tipo.equals(that.tipo) && tipoDebil.equals(that.tipoDebil);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tipo, tipoDebil);
    }
}
