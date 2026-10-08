package br.com.unitalent.msempresas.controller;
import br.com.unitalent.msempresas.dto.*;
import br.com.unitalent.msempresas.service.EmpresaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/empresas")
public class EmpresaController {
    private final EmpresaService service;
    public EmpresaController(EmpresaService service) { this.service = service; }
    @GetMapping public List<EmpresaResponse> findAll() { return service.findAll(); }
    @GetMapping("/<built-in function id>") public EmpresaResponse findById(@PathVariable Long id) { return service.findById(id); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public EmpresaResponse create(@Valid @RequestBody EmpresaRequest req) { return service.create(req); }
    @PutMapping("/<built-in function id>") public EmpresaResponse update(@PathVariable Long id, @Valid @RequestBody EmpresaRequest req) { return service.update(id, req); }
    @DeleteMapping("/<built-in function id>") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { service.delete(id); }
}
