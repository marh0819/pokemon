package co.edu.uceva.pokemon.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uceva.pokemon.persistence.entities.DebilidadesEntity;
import co.edu.uceva.pokemon.persistence.entities.DebilidadesId;
import co.edu.uceva.pokemon.persistence.repositories.DebilidadesRepository;

@Service
public class DebilidadesService {

    private final DebilidadesRepository debilidadesRepository;

    @Autowired
    public DebilidadesService(DebilidadesRepository debilidadesRepository) {
        this.debilidadesRepository = debilidadesRepository;
    }

    // Método para obtener todas las debilidades
    public List<DebilidadesEntity> getAllDebilidades() {
        return debilidadesRepository.findAll();
    }

    // Método para obtener una debilidad por idTipo y idTipoDebil
    public Optional<DebilidadesEntity> getDebilidad(Long idTipo, Long idTipoDebil) {
        DebilidadesId debilidadesId = new DebilidadesId(idTipo, idTipoDebil);
        return debilidadesRepository.findById(debilidadesId);
    }

    // Método para guardar una debilidad
    public void save(DebilidadesEntity debilidad) {
        debilidadesRepository.save(debilidad);
    }

    // Método para actualizar una debilidad (reutiliza el método save)
    public void update(DebilidadesEntity debilidad) {
        save(debilidad);
    }

    // Método para eliminar una debilidad por idTipo y idTipoDebil
    public void delete(Long idTipo, Long idTipoDebil) {
        DebilidadesId debilidadesId = new DebilidadesId(idTipo, idTipoDebil);
        debilidadesRepository.deleteById(debilidadesId);
    }
}
