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

import co.edu.uceva.pokemon.persistence.entities.TiposEntity;
import co.edu.uceva.pokemon.services.TiposService;

@RestController
@RequestMapping(path = "api/v1/tipos")
public class TiposController {

    private TiposService tiposService;

    @Autowired
    public TiposController(TiposService tiposService) {
        this.tiposService = tiposService;
    }

    @GetMapping("/listar")
    public List<TiposEntity> getAll() {
        return tiposService.getTipos();
    }

    @GetMapping("/{idTipos}")
    public Optional<TiposEntity> getByID(@PathVariable("idTipos") Long idTipos) { // Cambiar a Long
        return tiposService.getTipos(idTipos.intValue());
    }

    @PostMapping("/guardar")
    public void save(@RequestBody TiposEntity tipos) {
        tiposService.save(tipos);
    }

    @PostMapping("/actualizar/{idTipos}")
    public void update(@PathVariable Long idTipos, @RequestBody TiposEntity tipos) {
        tipos.setIdTipo(idTipos); // No es necesario convertir a int
        tiposService.update(tipos);
    }

    @DeleteMapping("/{idTipos}")
    public void delete(@PathVariable("idTipos") Long idTipos) { // Cambiar a Long
        tiposService.delete(idTipos.intValue());
    }
}
