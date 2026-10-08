package br.com.unitalent.msestudantes.controller;

import br.com.unitalent.msestudantes.dto.*;
import br.com.unitalent.msestudantes.service.EstudanteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudantes")
public class EstudanteController {

    private final EstudanteService service;

    public EstudanteController(EstudanteService service) {
        this.service = service;
    }

    @GetMapping
    public List<EstudanteResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public EstudanteResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EstudanteResponse create(@Valid @RequestBody EstudanteRequest req) {
        return service.create(req);
    }

    @PutMapping("/{id}")
    public EstudanteResponse update(
            @PathVariable Long id,
            @Valid @RequestBody EstudanteRequest req) {
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}