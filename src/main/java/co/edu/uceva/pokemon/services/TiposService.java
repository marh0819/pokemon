package co.edu.uceva.pokemon.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uceva.pokemon.persistence.entities.PokemonEntity;
import co.edu.uceva.pokemon.persistence.entities.TiposEntity;
import co.edu.uceva.pokemon.persistence.repositories.TiposRepository;
import co.edu.uceva.pokemon.persistence.repositories.PokemonRepository;

@Service
public class TiposService {
    private final TiposRepository tiposRepository;
    private final PokemonRepository pokemonRepository; // Añadir el repositorio de Pokémon

    @Autowired
    public TiposService(TiposRepository tiposRepository, PokemonRepository pokemonRepository) {
        this.tiposRepository = tiposRepository;
        this.pokemonRepository = pokemonRepository; // Inicializar el repositorio de Pokémon
    }

    public List<TiposEntity> getTipos() {
        return tiposRepository.findAll();
    }

    public Optional<TiposEntity> getTipos(int id) {
        // Convertir int a Long
        return tiposRepository.findById(Long.valueOf(id)); // Conversión a Long
    }

    public void save(TiposEntity tipos) {
        tiposRepository.save(tipos);
    }

    public void update(TiposEntity tipos) {
        tiposRepository.save(tipos);
    }

    public void delete(int id) {
        tiposRepository.deleteById(Long.valueOf(id));
    }

    // Método opcional para obtener Pokémon por tipo
    public List<PokemonEntity> getPokemonByTipo(int tipoId) {
        return pokemonRepository.findAll().stream()
                .filter(pokemon -> (pokemon.getTipoPrimario() != null
                        && pokemon.getTipoPrimario().getIdTipo() == tipoId) ||
                        (pokemon.getTipoSecundario() != null && pokemon.getTipoSecundario().getIdTipo() == tipoId))
                .toList();
    }
}
