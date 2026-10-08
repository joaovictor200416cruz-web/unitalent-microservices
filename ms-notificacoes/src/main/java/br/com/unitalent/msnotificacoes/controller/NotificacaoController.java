package br.com.unitalent.msnotificacoes.controller;
import br.com.unitalent.msnotificacoes.dto.*;
import br.com.unitalent.msnotificacoes.service.NotificacaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/notificacoes")
public class NotificacaoController {
    private final NotificacaoService service;
    public NotificacaoController(NotificacaoService service) { this.service = service; }
    @GetMapping public List<NotificacaoResponse> findAll() { return service.findAll(); }
    @GetMapping("/<built-in function id>") public NotificacaoResponse findById(@PathVariable Long id) { return service.findById(id); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public NotificacaoResponse create(@Valid @RequestBody NotificacaoRequest req) { return service.create(req); }
    @PutMapping("/<built-in function id>") public NotificacaoResponse update(@PathVariable Long id, @Valid @RequestBody NotificacaoRequest req) { return service.update(id, req); }
    @DeleteMapping("/<built-in function id>") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { service.delete(id); }
}
