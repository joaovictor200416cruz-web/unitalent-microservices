package br.com.unitalent.msvagas.controller;

import br.com.unitalent.msvagas.dto.*;
import br.com.unitalent.msvagas.service.VagaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vagas")
public class VagaController {

    private final VagaService service;

    public VagaController(VagaService service) {
        this.service = service;
    }

    @GetMapping
    public List<VagaResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public VagaResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VagaResponse create(@Valid @RequestBody VagaRequest req) {
        return service.create(req);
    }

    @PutMapping("/{id}")
    public VagaResponse update(@PathVariable Long id, @Valid @RequestBody VagaRequest req) {
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}