package co.edu.uceva.pokemon.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uceva.pokemon.persistence.entities.HabilidadesEntity;
import co.edu.uceva.pokemon.services.HabilidadesService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "api/v1/habilidades")
public class HabilidadesController {

    private final HabilidadesService habilidadesService;

    @Autowired
    public HabilidadesController(HabilidadesService habilidadesService) {
        this.habilidadesService = habilidadesService;
    }

    @GetMapping("/listar")
    public List<HabilidadesEntity> getAll() {
        return habilidadesService.getHabilidades();
    }

    @GetMapping("/{idHabilidades}")
    public ResponseEntity<HabilidadesEntity> getByID(@PathVariable("idHabilidades") int idHabilidades) {
        return habilidadesService.getHabilidades(idHabilidades)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/guardar")
    public ResponseEntity<Void> save(@Valid @RequestBody HabilidadesEntity habilidad) {
        habilidadesService.save(habilidad);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/actualizar/{idHabilidades}")
    public void update(@PathVariable Long idPokemon, @RequestBody HabilidadesEntity pokemon) {
        habilidadesService.update(pokemon);
    }

    @DeleteMapping("/{idHabilidades}")
    public ResponseEntity<Void> delete(@PathVariable("idHabilidades") int idHabilidades) {
        habilidadesService.delete(idHabilidades);
        return ResponseEntity.noContent().build();
    }
}
