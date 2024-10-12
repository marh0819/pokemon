package co.edu.uceva.pokemon.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uceva.pokemon.persistence.entities.HabilidadesEntity;
import co.edu.uceva.pokemon.services.HabilidadesService;

@RestController
@RequestMapping(path = "api/v1/habilidades")
public class HabilidadesController {

    private HabilidadesService habilidadesService;

    @Autowired
    public HabilidadesController(HabilidadesService habilidadesService) {
        this.habilidadesService = habilidadesService;
    }

    @GetMapping("/listar")
    public List<HabilidadesEntity> getAll() {
        return habilidadesService.getHabilidades();
    }

    @GetMapping("/{idHabilidades}")
    public Optional<HabilidadesEntity> getByID(@PathVariable("idHabilidades") int idPokemon) {
        return habilidadesService.getHabilidades(idPokemon);
    }

    @PostMapping("/guardar")
    public void save(@RequestBody HabilidadesEntity pokemon) {
        habilidadesService.save(pokemon);
    }

    @PostMapping("/actualizar/{idHabilidades}")
    public void update(@PathVariable Long idPokemon, @RequestBody HabilidadesEntity pokemon) {
        habilidadesService.update(pokemon);
    }

    @DeleteMapping("/{idHabilidades}")
    public void delete(@PathVariable("idHabilidades") int idPokemon) {
        habilidadesService.delete(idPokemon);
    }

}
