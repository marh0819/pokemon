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

import co.edu.uceva.pokemon.persistence.entities.DebilidadesEntity;
import co.edu.uceva.pokemon.services.DebilidadesService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "api/v1/debilidades")
public class DebilidadesController {

    private final DebilidadesService debilidadesService;

    @Autowired
    public DebilidadesController(DebilidadesService debilidadesService) {
        this.debilidadesService = debilidadesService;
    }

    @GetMapping("/listar")
    public List<DebilidadesEntity> getAll() {
        return debilidadesService.getAllDebilidades();
    }

    @GetMapping("/{idTipo}/{idTipoDebil}")
    public ResponseEntity<DebilidadesEntity> getByID(@PathVariable("idTipo") int idTipo, 
                                                      @PathVariable("idTipoDebil") int idTipoDebil) {
        return debilidadesService.getDebilidad(idTipo, idTipoDebil)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/guardar")
    public ResponseEntity<Void> save(@Valid @RequestBody DebilidadesEntity debilidad) {
        debilidadesService.save(debilidad);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/actualizar/{idTipo}/{idTipoDebil}")
    public ResponseEntity<Void> update(
        @PathVariable("idTipo") int idTipo,
        @PathVariable("idTipoDebil") int idTipoDebil,
        @Valid @RequestBody DebilidadesEntity debilidad) {
        debilidadesService.update(debilidad);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{idTipo}/{idTipoDebil}")
    public ResponseEntity<Void> delete(@PathVariable("idTipo") int idTipo, 
                                        @PathVariable("idTipoDebil") int idTipoDebil) {
        debilidadesService.delete(idTipo, idTipoDebil);
        return ResponseEntity.noContent().build();
    }
}
