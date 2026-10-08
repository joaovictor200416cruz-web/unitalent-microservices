package br.com.unitalent.mscandidaturas.controller;

import br.com.unitalent.mscandidaturas.dto.*;
import br.com.unitalent.mscandidaturas.service.CandidaturaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/candidaturas")
public class CandidaturaController {
    private final CandidaturaService service;
    public CandidaturaController(CandidaturaService service) { this.service = service; }

    @GetMapping public List<CandidaturaResponse> findAll() { return service.findAll(); }
    @GetMapping("/{id}") public CandidaturaResponse findById(@PathVariable Long id) { return service.findById(id); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public CandidaturaResponse create(@Valid @RequestBody CandidaturaRequest req) { return service.create(req); }
}
