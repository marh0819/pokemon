package co.edu.uceva.pokemon.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import co.edu.uceva.pokemon.persistence.entities.DebilidadesEntity;
import co.edu.uceva.pokemon.persistence.entities.DebilidadesId;
import co.edu.uceva.pokemon.services.DebilidadesService;

import co.edu.uceva.pokemon.persistence.entities.TiposEntity;

@RestController
@RequestMapping(path = "api/v1/debilidades")
public class DebilidadesController {

    private final DebilidadesService debilidadesService;

    @Autowired
    public DebilidadesController(DebilidadesService debilidadesService) {
        this.debilidadesService = debilidadesService;
    }

    // Método para listar todas las debilidades
    @GetMapping("/listar")
    public List<DebilidadesEntity> getAll() {
        return debilidadesService.getAllDebilidades();
    }

    // Método para obtener una debilidad específica por idTipo y idTipoDebil
    @GetMapping("/{idTipo}/{idTipoDebil}")
    public Optional<DebilidadesEntity> getById(@PathVariable("idTipo") Long idTipo,
            @PathVariable("idTipoDebil") Long idTipoDebil) {
        return debilidadesService.getDebilidad(idTipo, idTipoDebil);
    }

    // Método para guardar una nueva debilidad
    @PostMapping("/guardar")
    public void save(@RequestBody DebilidadesEntity debilidad) {
        debilidadesService.save(debilidad);
    }

    @PostMapping("/actualizar/{idTipo}/{idTipoDebil}")
    public void update(@PathVariable("idTipo") Long idTipo,
            @PathVariable("idTipoDebil") Long idTipoDebil,
            @RequestBody DebilidadesEntity debilidad) {

        // Crear instancias de TiposEntity con los ids correspondientes
        TiposEntity tipo = new TiposEntity();
        tipo.setIdTipo(idTipo);

        TiposEntity tipoDebil = new TiposEntity();
        tipoDebil.setIdTipo(idTipoDebil);

        // Asignar los tipos a la entidad de debilidad
        debilidad.setIdTipo(tipo);
        debilidad.setIdTipoDebil(tipoDebil);

        // Llamar al servicio para actualizar la entidad
        debilidadesService.update(debilidad);
    }

    // Método para eliminar una debilidad por idTipo y idTipoDebil
    @DeleteMapping("/{idTipo}/{idTipoDebil}")
    public void delete(@PathVariable("idTipo") Long idTipo,
            @PathVariable("idTipoDebil") Long idTipoDebil) {
        debilidadesService.delete(idTipo, idTipoDebil);
    }
}
