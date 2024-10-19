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

    public List<DebilidadesEntity> getAllDebilidades() {
        return debilidadesRepository.findAll();
    }

    public Optional<DebilidadesEntity> getDebilidad(int idTipo, int idTipoDebil) {
        DebilidadesId debilidadesId = new DebilidadesId(idTipo, idTipoDebil);
        return debilidadesRepository.findById(debilidadesId);
    }

    public void save(DebilidadesEntity debilidad) {
        debilidadesRepository.save(debilidad);
    }

    public void update(DebilidadesEntity debilidad) {
        save(debilidad); // Reutiliza el método save
    }

    public void delete(int idTipo, int idTipoDebil) {
        DebilidadesId debilidadesId = new DebilidadesId(idTipo, idTipoDebil);
        debilidadesRepository.deleteById(debilidadesId);
    }
}
