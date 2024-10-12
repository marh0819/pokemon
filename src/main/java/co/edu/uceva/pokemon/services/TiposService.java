package co.edu.uceva.pokemon.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uceva.pokemon.persistence.entities.TiposEntity;
import co.edu.uceva.pokemon.persistence.repositories.TiposRepository;

@Service
public class TiposService {
    private final TiposRepository tiposRepository;

    @Autowired
    public TiposService(TiposRepository tiposRepository) {
        this.tiposRepository = tiposRepository;
    }

    public List<TiposEntity> getTipos() {
        return tiposRepository.findAll();
    }

    public Optional<TiposEntity> getTipos(int id){
        return tiposRepository.findById(Long.valueOf (id));
    }

    public void save(TiposEntity tipos){
        tiposRepository.save(tipos);
    }

    public void update(TiposEntity tipos){
        tiposRepository.save(tipos);
    }

    public void delete(int id){
        tiposRepository.deleteById(Long.valueOf (id));
    }
}
