package co.edu.uceva.pokemon.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uceva.pokemon.persistence.entities.HabilidadesEntity;
import co.edu.uceva.pokemon.persistence.repositories.HabilidadesRepository;

@Service
public class HabilidadesService {
    private final HabilidadesRepository habilidadesRepository;

    @Autowired
    public HabilidadesService(HabilidadesRepository habilidadesRepository) {
        this.habilidadesRepository = habilidadesRepository;
    }

    public List<HabilidadesEntity> getHabilidades() {
        return habilidadesRepository.findAll();
    }

    public Optional<HabilidadesEntity> getHabilidades(int id) {
        return habilidadesRepository.findById(Long.valueOf(id));
    }

    public void save(HabilidadesEntity habilidad) {
        habilidadesRepository.save(habilidad);
    }

    public void update(HabilidadesEntity pokemon){
        habilidadesRepository.save(pokemon);
    }

    public void delete(int id) {
        habilidadesRepository.deleteById(Long.valueOf(id));
    }
}
