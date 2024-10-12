package co.edu.uceva.pokemon.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uceva.pokemon.persistence.entities.PokemonEntity;
import co.edu.uceva.pokemon.persistence.entities.TiposEntity;
import co.edu.uceva.pokemon.persistence.repositories.PokemonRepository;
import co.edu.uceva.pokemon.persistence.repositories.TiposRepository;

@Service
public class PokemonService {
    private final PokemonRepository pokemonRepository;
    private final TiposRepository tiposRepository;

    @Autowired
    public PokemonService(PokemonRepository pokemonRepository, TiposRepository tiposRepository) {
        this.pokemonRepository = pokemonRepository;
        this.tiposRepository = tiposRepository;
    }

    public List<PokemonEntity> getPokemon() {
        return pokemonRepository.findAll();
    }

    public Optional<PokemonEntity> getPokemon(int id) {
        return pokemonRepository.findById(Long.valueOf(id));
    }

    public void save(PokemonEntity pokemon) {
        if (pokemon.getTipoPrimario() != null && pokemon.getTipoPrimario().getIdTipo() != null) {
            TiposEntity tipoPrimario = tiposRepository.findById(pokemon.getTipoPrimario().getIdTipo()).orElse(null);
            if (tipoPrimario != null) {
                pokemon.setTipoPrimario(tipoPrimario);
            } else {
                throw new RuntimeException("Tipo primario no encontrado");
            }
        }
        if (pokemon.getTipoSecundario() != null && pokemon.getTipoSecundario().getIdTipo() != null) {
            TiposEntity tipoSecundario = tiposRepository.findById(pokemon.getTipoSecundario().getIdTipo()).orElse(null);
            if (tipoSecundario != null) {
                pokemon.setTipoSecundario(tipoSecundario);
            } else {
                throw new RuntimeException("Tipo secundario no encontrado");
            }
        }
        pokemonRepository.save(pokemon);
    }

    public void update(PokemonEntity pokemon) {
        save(pokemon); // Llamar a save para manejar la lógica de guardado
    }

    public void delete(int id) {
        pokemonRepository.deleteById(Long.valueOf(id));
    }
}
