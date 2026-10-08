package br.com.unitalent.msvagas.service;
    import br.com.unitalent.msvagas.dto.*;
    import br.com.unitalent.msvagas.model.Vaga;
    import br.com.unitalent.msvagas.repository.VagaRepository;
    import org.springframework.stereotype.Service;
    import java.util.List;
    import java.util.stream.Collectors;

    @Service
    public class VagaService {
        private final VagaRepository repository;
        public VagaService(VagaRepository repository) { this.repository = repository; }

        public List<VagaResponse> findAll() {
            return repository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
        }
        public VagaResponse findById(Long id) {
            return toResponse(repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Vaga não encontrado")));
        }
        public VagaResponse create(VagaRequest req) {
            Vaga entity = new Vaga(req.titulo(), req.descricao(), req.requisitos(), req.empresaId(), req.status());
            return toResponse(repository.save(entity));
        }
        public VagaResponse update(Long id, VagaRequest req) {
            Vaga entity = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Vaga não encontrado"));
            entity.setTitulo(req.titulo());
entity.setDescricao(req.descricao());
entity.setRequisitos(req.requisitos());
entity.setEmpresaId(req.empresaId());
entity.setStatus(req.status());
            return toResponse(repository.save(entity));
        }
        public void delete(Long id) { repository.deleteById(id); }
        private VagaResponse toResponse(Vaga entity) {
            return new VagaResponse(entity.getId(), entity.getTitulo(), entity.getDescricao(), entity.getRequisitos(), entity.getEmpresaId(), entity.getStatus());
        }
    }
