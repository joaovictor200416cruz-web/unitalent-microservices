package br.com.unitalent.msempresas.service;
    import br.com.unitalent.msempresas.dto.*;
    import br.com.unitalent.msempresas.model.Empresa;
    import br.com.unitalent.msempresas.repository.EmpresaRepository;
    import org.springframework.stereotype.Service;
    import java.util.List;
    import java.util.stream.Collectors;

    @Service
    public class EmpresaService {
        private final EmpresaRepository repository;
        public EmpresaService(EmpresaRepository repository) { this.repository = repository; }

        public List<EmpresaResponse> findAll() {
            return repository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
        }
        public EmpresaResponse findById(Long id) {
            return toResponse(repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Empresa não encontrado")));
        }
        public EmpresaResponse create(EmpresaRequest req) {
            Empresa entity = new Empresa(req.razaoSocial(), req.cnpj(), req.email(), req.descricao());
            return toResponse(repository.save(entity));
        }
        public EmpresaResponse update(Long id, EmpresaRequest req) {
            Empresa entity = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Empresa não encontrado"));
            entity.setRazaoSocial(req.razaoSocial());
entity.setCnpj(req.cnpj());
entity.setEmail(req.email());
entity.setDescricao(req.descricao());
            return toResponse(repository.save(entity));
        }
        public void delete(Long id) { repository.deleteById(id); }
        private EmpresaResponse toResponse(Empresa entity) {
            return new EmpresaResponse(entity.getId(), entity.getRazaoSocial(), entity.getCnpj(), entity.getEmail(), entity.getDescricao());
        }
    }
