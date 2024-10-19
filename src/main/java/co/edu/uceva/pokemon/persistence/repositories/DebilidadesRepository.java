package co.edu.uceva.pokemon.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import co.edu.uceva.pokemon.persistence.entities.DebilidadesEntity;
import co.edu.uceva.pokemon.persistence.entities.DebilidadesId;

public interface DebilidadesRepository extends JpaRepository<DebilidadesEntity, DebilidadesId> {
}
